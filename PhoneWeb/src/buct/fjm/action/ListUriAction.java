package buct.fjm.action;

import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import buct.fjm.jsonConver.ConverUriListToJson;
import buct.fjm.model.Pkguri;
import buct.fjm.model.Uri;
import buct.fjm.service.PkgService;
import buct.fjm.service.PkgUriService;
import buct.fjm.service.UriService;

import com.opensymphony.xwork2.ActionSupport;
@SuppressWarnings("serial")
@Component ("listUri")
@Scope ("prototype")
public class ListUriAction extends ActionSupport{
	private String start = null;
	private String limit = null;
	private String pkgId=null;
	private PkgUriService pkgUriService;
	private UriService uriService;
	private PkgService pkgService;
	private URL url;
	private HttpURLConnection conn;
	private Integer state = -1;
	
	@Resource
	public void setPkgService(PkgService pkgService) {
		this.pkgService = pkgService;
	}
	public PkgService getPkgService() {
		return pkgService;
	}
	public PkgUriService getPkgUriService() {
		return pkgUriService;
	}
	@Resource
	public void setPkgUriService(PkgUriService pkgUriService) {
		this.pkgUriService = pkgUriService;
	}
	public UriService getUriService() {
		return uriService;
	}
	@Resource
	public void setUriService(UriService uriService) {
		this.uriService = uriService;
	}
	public String execute() throws Exception
	{
		/*HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		String json ="";
		if(pkgId!=null)
		{
			//Phoneip phoneip=phoneipService.findById(Integer.parseInt(phoneid));
			String hql="from Uri as model where model.pkg="+pkgId;
			String countHQL="select count(*) from Uri as model where model.pkg="+pkgId;
			//List<Pagcontent> allInfos=pagcontentService.findByProperty("phoneip", phoneip);
			int totalCount=uriService.getCount(countHQL);
			List<Uri> allInfos=uriService.getListBySql(hql, Integer.parseInt(start), Integer.parseInt(limit));
			json = ConverUriListToJson.ConverListToPageJson(allInfos, totalCount);
		}
		// 获取流
		PrintWriter out = response.getWriter();
		// 将数据以json格式打到客户端
		out.print(json);
		System.out.println(json);
		// 清空缓存
		out.flush();
		// 关闭流
		out.close();*/
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		String json ="";
		/*HibernateUtil searchHibernate;
		searchHibernate=new HibernateUtil();*/
		if(pkgId!=null)
		{
			//Pkg pkg=pkgService.findById(Integer.parseInt(pkgId));
			String hql="from Pkguri as model where model.pkg="+Integer.parseInt(pkgId)+" order by uriId desc";
			String countHQL="select count(*) from Pkguri as model where model.pkg="+pkgId;
			int totalCount=pkgUriService.getCount(countHQL);
			List<Pkguri> pkguris=pkgUriService.getListBySql(hql, Integer.parseInt(start), totalCount);
			//List<Pkguri> pkguriList=searchHibernate.Search(hql);
			List<Uri> uris=new ArrayList<Uri>();
			for(Pkguri temp:pkguris){
				uris.add(uriService.findById(temp.getUri().getUriId()));
			}
//		   for(Uri temp1:uris){
//			   String testUrl=temp1.getHost()+temp1.getPath();
//			    if(!testUrl.startsWith("http")){
//			    	testUrl = "http://" + testUrl;
//			    	System.out.println(testUrl);
//			    }
//			    //test whether can connect
//			    url = new URL(testUrl);
//		     	 conn = (HttpURLConnection)url.openConnection();
//		     	 state = conn.getResponseCode();
//		     	 if(state != 200){
//		     		 uris.remove(temp1);
//		     	 }
//			    
//		   }
			json = ConverUriListToJson.ConverListToPageJson(uris, totalCount);
		}
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
	public String getPkgId() {
		return pkgId;
	}
	public void setPkgId(String pkgId) {
		this.pkgId = pkgId;
	}
	
	
	
}
