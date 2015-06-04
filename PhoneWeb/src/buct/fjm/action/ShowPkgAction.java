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
import buct.fjm.jsonConver.PkgDetor;
import buct.fjm.model.Accesscontrol;
import buct.fjm.model.Pkg;
import buct.fjm.model.Pkguri;
import buct.fjm.model.Uri;
import buct.fjm.service.AccessService;
import buct.fjm.service.ClientService;
import buct.fjm.service.PkgService;
import buct.fjm.service.PkgUriService;
import buct.fjm.service.UriService;

import com.opensymphony.xwork2.ActionSupport;
@SuppressWarnings("serial")
@Component ("showPkg")
@Scope ("prototype")
public class ShowPkgAction extends ActionSupport{
	private PkgService pkgService;
	private String start = null;
	private String limit = null;
	private PkgUriService pkgUriService;
	private UriService uriService;
	private ClientService clientService;
	private AccessService accessService;
	
	
	public AccessService getAccessService() {
		return accessService;
	}

	@Resource
	public void setAccessService(AccessService accessService) {
		this.accessService = accessService;
	}
	
	@Resource
	public void setPkgService(PkgService pkgService) {
		this.pkgService = pkgService;
	}
	public PkgService getPkgService() {
		return pkgService;
	}
	@Resource
	public void setPkgUriService(PkgUriService pkgUriService) {
		this.pkgUriService = pkgUriService;
	}
	
	public PkgUriService getPkgUriService() {
		return pkgUriService;
	}

	@Resource
	public void setUriService(UriService uriService) {
		this.uriService = uriService;
	}

	public UriService getUriService() {
		return uriService;
	}

	
	public ClientService getClientService() {
		return clientService;
	}

	@Resource
	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
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
		String hql="from Pkg where dateVisit='"+sdf.format(dt)+"' order by lastVisitTime desc";
		//String hql="from Pkg order by lastVisitTime desc";
		String countHQL="select count(*) from Pkg where dateVisit='"+sdf.format(dt)+"'";
		//String countHQL="select count(*) from Pkg";
		//List<Pagcontent> allInfos=pagcontentService.findByProperty("phoneip", phoneip);
		String macHQL = "select distinct cmac from Clientlist where showpkgs=1";
		int totalCount=pkgService.getCount(countHQL);
		List<Pkg> allInfos=pkgService.getListBySql(hql, Integer.parseInt(start), Integer.parseInt(limit));
		List<Accesscontrol> accessList=accessService.findAll();
		List macList = clientService.queryMac(macHQL);
		Map<String,Short> lists=new HashMap<String,Short>();
		for(Accesscontrol AC:accessList){
			lists.put(AC.getIp()+":"+AC.getPort(),AC.getAccessType());
		}
		List<PkgDetor> acList=new ArrayList();
		List uris = new ArrayList();
		for(Pkg pkg:allInfos){
//			try{
//				String pkgUriQueryHql="from Pkguri as p inner join fetch p.Pkg pg where pg.packageId="+pkg.getPackageId();
//				   List<Pkguri> pkgUriResultList=pkgUriService.getList(pkgUriQueryHql);
//				   List<Uri> uri = new ArrayList<Uri>();
//				   for(Pkguri temp:pkgUriResultList){
//				    	String uriId=temp.getUri().getUriId().toString();
//				    	uri.add(uriService.findById(Integer.parseInt(uriId)));
//				    }
//				   for(Uri temp1:uri){
//						String testpath = temp1.getPath();
//						if(testpath.contains(".jpg") || testpath.contains(".png") || testpath.contains(".gif") || testpath.contains(".bmp")){
//							if(pkg.getProtocolType() == 6){
//								pkg.setProtocolType((short) 60);
//								break;
//							}
//							else if(pkg.getProtocolType() == 17){
//								pkg.setProtocolType((short) 170);
//								break;
//							}
//						}
//					}
//			}catch(Exception e){
//				e.printStackTrace();
//			}
//		   
			if(macList.contains(pkg.getNativeMac())){
				//处理数据包，标记含有图片的数据包   
				int pkgId = pkg.getPackageId();
				String uriHQL ="from Pkguri as model where model.pkg="+pkgId+" order by uriId desc";
				String uriCountHQL ="select count(*) from Pkguri as model where model.pkg="+pkgId;
				int totalUriCount = pkgUriService.getCount(uriCountHQL);
				List<Pkguri> pkguri = pkgUriService.getListBySql(uriHQL, 0,totalUriCount);
				List<Uri> uri = new ArrayList<Uri>();
				for(Pkguri temp:pkguri){
					uri.add(uriService.findById(temp.getUri().getUriId()));
				}
				for(Uri temp1:uri){
					String testpath = temp1.getPath();
					if(testpath.contains(".jpg") || testpath.contains(".png") || testpath.contains(".gif") || testpath.contains(".bmp")){
						if(pkg.getProtocolType() == 6){
							pkg.setProtocolType((short) 60);
							break;
						}
						else if(pkg.getProtocolType() == 17){
							pkg.setProtocolType((short) 170);
							break;
						}
					}
				}
				
				if(lists.keySet().contains(pkg.getRemoteIp()+":"+pkg.getRemotePort())){
					if(lists.get(pkg.getRemoteIp()+":"+pkg.getRemotePort())==1){
						acList.add(new PkgDetor(pkg,2));
					}else if(lists.get(pkg.getRemoteIp()+":"+pkg.getRemotePort())==0){
						acList.add(new PkgDetor(pkg,3));
					}
				}else if(Integer.parseInt(pkg.getFlowAmount())>3000&&!pkg.getFlowDirectioin())
				{
					acList.add(new PkgDetor(pkg,1));
				}else{
					acList.add(new PkgDetor(pkg,0));
				}
			}
		}
		
		json = ConverAccessListToJson.ConverListToPageJson(acList, totalCount);
		json = json.replace(":6,", ":\"HTTP/TCP\",");
		json = json.replace(":17,", ":\"UDP\",");
		json = json.replace(":60,", ":\"HTTP/TCP*\",");
		json = json.replace(":170,", ":\"UDP*\",");
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
