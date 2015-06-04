package buct.fjm.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import buct.fjm.jsonConver.ConverAccessListToJson;
import buct.fjm.model.Pkg;
import buct.fjm.model.Safety;
import buct.fjm.service.PkgService;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Component ("showMac")
@Scope ("prototype")
public class ShowMacAction extends ActionSupport{
	private PkgService pkgService;
	private double MaxS=0.1;
	@Resource
	public void setPkgService(PkgService pkgService) {
		this.pkgService = pkgService;
	}
	public PkgService getPkgService() {
		return pkgService;
	}
	
	public String execute() throws Exception{
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		String json ="";
		
		Date dt=new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		List<Pkg> pList=new ArrayList<Pkg>();
		//String hql="select nativeMac from Pkg where dateVisit='"+sdf.format(dt)+"'order by lastVisitTime desc";
		//String hql="from pkg where dateVisit='"+sdf.format(dt)+"' group by nativeMac,remoteIp,remotePort order by lastVisitTime desc";
		//String hql="from Pkg where dateVisit='"+sdf.format(dt)+"' order by lastVisitTime desc";
		String hql="from Pkg where dateVisit='"+"2014-05-04"+"' order by lastVisitTime desc";
		pList=pkgService.getList(hql);
		int count=pList.size();
		
		List<Safety> safeList=new ArrayList<Safety>();
		
		Map<String,Double> macIp=new HashMap<String,Double>();
/*		Map<String,Double> IpFlow=new HashMap<String,Double>();*/
		for(Pkg pkg:pList){
			String idStr=pkg.getNativeMac()+" "+pkg.getRemoteIp()+":"+pkg.getRemotePort();
			if(!macIp.keySet().contains(idStr)){
				macIp.put(idStr, Double.parseDouble(pkg.getFlowAmount()));
			}else{
				if(pkg.getFlowDirectioin()){
					double scale=macIp.get(idStr)/(macIp.get(idStr)+Double.parseDouble(pkg.getFlowAmount()));
					macIp.put(idStr, scale);
				}else if(pkg.getFlowDirectioin()){
					double scale=Double.parseDouble(pkg.getFlowAmount())/(macIp.get(idStr)+Double.parseDouble(pkg.getFlowAmount()));
					macIp.put(idStr, scale);
				}
				
			}
		}
		
		Map<String,Double> macMax=new HashMap<String,Double>();
		for(String id:macIp.keySet()){
			if(macIp.get(id)>1){
				continue;
			}
			String temp[]=id.split(" ");
			if(!macMax.keySet().contains(temp[0])){
				macMax.put(temp[0], macIp.get(id));
			}else{
				if(macMax.get(temp[0])<macIp.get(id)){
					macMax.put(temp[0], macIp.get(id));
				}
			}
		}
		
		for(String mac:macMax.keySet()){
			Safety safety=new Safety();
			safety.setNativeMac(mac);
			safety.setMaxUpDown(macMax.get(mac));
			if(macMax.get(mac)>MaxS){
				safety.setType(2+"");
			}
			safeList.add(safety);
		}
		/*for(String mac:macList){
			//String ipHql="select remoteIp from Pkg where nativeMac='"+mac+"' and dateVisit='"+sdf.format(dt)+"'";
			String ipHql="select remoteIp from Pkg where nativeMac='"+mac+"' group by remoteIp";
			List<String> ipList=pkgService.getMacList(ipHql);
			for(String ip:ipList){
				//String upHql="select flowAmount from Pkg where remoteIp='"+ip+"' and dateVisit='"+sdf.format(dt)+"' and flowDirectioin="+0;
				String upHql="select flowAmount from Pkg where remoteIp='"+ip+"' and flowDirectioin="+0;
				double upAmount=0;
				for(String amount:pkgService.getMacList(upHql)){
					upAmount+=Double.parseDouble(amount);
				}
				
				//String downHql="select flowAmount from Pkg where remoteIp='"+ip+"' and dateVisit='"+sdf.format(dt)+"' and flowDirectioin="+1;
				String downHql="select flowAmount from Pkg where remoteIp='"+ip+"' and flowDirectioin="+1;
				double downAmount=0;
				for(String amount:pkgService.getMacList(downHql)){
					downAmount+=Double.parseDouble(amount);
				}
				
				double scale=upAmount/(downAmount+upAmount);
				if(maxScale<scale){
					maxScale=scale;
				}
			}
			
			Safety safety=new Safety();
			safety.setNativeMac(mac);
			safety.setMaxUpDown(maxScale);
			if(maxScale>MaxS){
				safety.setType(2+"");
			}
			safeList.add(safety);
		}*/
		
		json = ConverAccessListToJson.ConverListToPageJson(safeList, count);
		
		// 获取流
		PrintWriter out = response.getWriter();
		// 将数据以json格式打到客户端
		out.print(json);
		System.out.println(json);
		// 清空缓存
		out.flush();
		// 关闭流
		out.close();
		return null;
	}
}
