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
import buct.fjm.model.Clientlist;
import buct.fjm.model.Tpkg;
import buct.fjm.service.AccessService;
import buct.fjm.service.TPkgService;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Component("getTPkgMac")
@Scope("prototype")
public class GetTPkgMacAction extends ActionSupport{
	private TPkgService tPkgService;
	
	public TPkgService getTPkgService(){
		return tPkgService;
	}
	
	@Resource
	public void setTPkgService(TPkgService tPkgService){
		this.tPkgService = tPkgService;
	}
	
	public String execute() throws Exception{
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		String json ="[";
		
		String hql = "select distinct tnativeMac from Tpkg";
		List tPkgMacList = tPkgService.queryMac(hql);
		//System.out.println(cMacList.get(1));
		//json = cMacList.toString();
		//json = ConverAccessListToJson.ConverListToPageJson(cMacList, 1000);
		for(int i = 0; i < tPkgMacList.size();i++){
			json += "{tnativeMac:"+"\""+tPkgMacList.get(i)+"\"},";
		}
		json += "]";
		//json = "[{cMac:"+"\""+cMacList.get(1)+"\"},{cMac:"+"\""+cMacList.get(2)+"\"}]";
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