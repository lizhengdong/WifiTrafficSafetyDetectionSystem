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
import buct.fjm.jsonConver.TPkgDetor;
import buct.fjm.model.Accesscontrol;
import buct.fjm.model.Tpkg;
import buct.fjm.service.AccessService;
import buct.fjm.service.TPkgService;

import com.opensymphony.xwork2.ActionSupport;
@SuppressWarnings("serial")
@Component ("showTPkg")
@Scope ("prototype")
public class ShowTPkgAction extends ActionSupport{
	private TPkgService tPkgService;
	private String start = null;
	private String limit = null;
	
	private AccessService accessService;
	
	
	public AccessService getAccessService() {
		return accessService;
	}

	@Resource
	public void setAccessService(AccessService accessService) {
		this.accessService = accessService;
	}
	
	public TPkgService gettPkgService() {
		return tPkgService;
	}
	@Resource
	public void settPkgService(TPkgService tPkgService) {
		this.tPkgService = tPkgService;
	}

	public String execute() throws Exception
	{
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		String json ="";

		//Phoneip phoneip=phoneipService.findById(Integer.parseInt(phoneid));
		Date dt=new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//System.out.println(sdf.format(dt));
		String hql="from Tpkg order by tlastVisitTime desc";
		//String hql="from Pkg order by lastVisitTime desc";
		String countHQL="select count(*) from Tpkg";
		//String countHQL="select count(*) from Pkg";
		//List<Pagcontent> allInfos=pagcontentService.findByProperty("phoneip", phoneip);
		int totalCount=tPkgService.getCount(countHQL);
		List<Tpkg> allInfos=tPkgService.getListBySql(hql, Integer.parseInt(start), Integer.parseInt(limit));
		List<Accesscontrol> accessList=accessService.findAll();
		Map<String,Short> lists=new HashMap<String,Short>();
		for(Accesscontrol AC:accessList){
			lists.put(AC.getIp()+":"+AC.getPort(),AC.getAccessType());
		}
		List<TPkgDetor> acList=new ArrayList();
		for(Tpkg tpkg:allInfos){
			if(lists.keySet().contains(tpkg.getTremoteIp()+":"+tpkg.getTremotePort())){
				if(lists.get(tpkg.getTremoteIp()+":"+tpkg.getTremotePort())==1){
					acList.add(new TPkgDetor(tpkg,2));
				}else if(lists.get(tpkg.getTremoteIp()+":"+tpkg.getTremotePort())==0){
					acList.add(new TPkgDetor(tpkg,3));
				}
			}else if(Integer.parseInt(tpkg.getTflowAmount())>3000&&!tpkg.getTflowDirection())
			{
				acList.add(new TPkgDetor(tpkg,1));
			}else{
				acList.add(new TPkgDetor(tpkg,0));
			}
		}
		
		json = ConverAccessListToJson.ConverListToPageJson(acList, totalCount);
		json = json.replace(":6,", ":\"HTTP/TCP\",");
		json = json.replace(":17,", ":\"UDP\",");
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
	
	
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getLimit() {
		return limit;
	}
	public void setLimit(String limit) {
		this.limit = limit;
	}
	
}
