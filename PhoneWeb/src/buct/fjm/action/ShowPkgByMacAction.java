package buct.fjm.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import buct.fjm.jsonConver.ConverAccessListToJson;
import buct.fjm.jsonConver.TPkgDetor;
import buct.fjm.model.Tpkg;
import buct.fjm.service.TPkgService;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Component("showPkgByMac")
@Scope("prototype")
public class ShowPkgByMacAction extends ActionSupport{
	
	private String tnativeMac;
	private String start = null;
	private String limit = null;
	private TPkgService tPkgService;
	
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

	public String getTnativeMac() {
		return tnativeMac;
	}
	
	public void setTnativeMac(String tnativeMac) {
		this.tnativeMac = tnativeMac;
	}
	
	public TPkgService gettPkgService() {
		return tPkgService;
	}
	
	@Resource
	public void settPkgService(TPkgService tPkgService) {
		this.tPkgService = tPkgService;
	}
	
	public String execute() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		String json = "";
		
		String hql = "from Tpkg where tnativeMac=\'" + tnativeMac + "\'";
		String count = "select count(*) from Tpkg where tnativeMac=\'" + tnativeMac + "\'";
		int totalCount = tPkgService.getCount(count);
		
		List<Tpkg> all = tPkgService.getListBySql(hql, Integer.parseInt(start), Integer.parseInt(limit));
		//List pkgList = tPkgService.findByTnativeMac(tnativeMac);
		List pList = new ArrayList();
		for(Tpkg tpkg:all){
			pList.add(new TPkgDetor(tpkg,0));
		}
		
		json = ConverAccessListToJson.ConverListToPageJson(pList, totalCount);
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
	

}