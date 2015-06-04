package buct.fjm.action;

import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import buct.fjm.model.Clientlist;
import buct.fjm.service.ClientService;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Component("changePkgMode")
@Scope("prototype")
public class ChangePkgModeAction extends ActionSupport{
	private String cid;
	private String cmac;
	private Boolean showpkgs;
	private ClientService clientService;
	
	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getCmac() {
		return cmac;
	}
	
	public void setCmac(String cmac) {
		this.cmac = cmac;
	}
	public Boolean getShowpkgs() {
		return showpkgs;
	}
	
	public void setShowpkgs(Boolean showpkgs) {
		this.showpkgs = showpkgs;
	}
	public ClientService getClientService() {
		return clientService;
	}
	
	@Resource
	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}
	
	public String execute() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		String json = "";
		
		String hql = "select distinct cmac from Clientlist";
		List macList = clientService.queryMac(hql);
		
		Clientlist cl = new Clientlist();
		cl.setCid(Integer.parseInt(cid));
		cl.setCmac(cmac);
		cl.setShowpkgs(showpkgs);
		
		if(macList.contains(cmac)){
			String msg = clientService.update(cl);
			if(msg == "Success"){
				json = "{\"success\":true}";
			}
			else if(msg == "Failure"){
				json = "{\"success\":false}";
			}
		}
		else {
			json = "{\"success\":false}";
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

}