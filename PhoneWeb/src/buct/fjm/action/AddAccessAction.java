package buct.fjm.action;

import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import buct.fjm.service.AccessService;

import com.opensymphony.xwork2.ActionSupport;
import buct.fjm.model.Accesscontrol;

@SuppressWarnings("serial")
@Component ("addAccess")
@Scope ("prototype")
public class AddAccessAction extends ActionSupport{
	//private String aid;
	private String ip;
	private String port;
	private String accessType;
	private AccessService accessService;
	private AccessService getAccessService(){
		return accessService;
	}
	@Resource
	public void setAccessService(AccessService accessService){
		this.accessService = accessService;
	}
	/*public String getAid(){
		return aid;
	}
	public void setAid(String aid){
		this.aid = aid;
	}*/
	public String getIp(){
		return ip;
	}
	public void setIp(String ip){
		this.ip = ip;
	}
	public String getPort(){
		return port;
	}
	public void setPort(String port){
		this.port = port;
	}
	public String getAccessType(){
		return accessType;
	}
	public void setAccessType(String accessType){
		this.accessType = accessType;
	}
	public String execute() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		Accesscontrol accesscontrol = new Accesscontrol();
		//accesscontrol.setAid(Integer.parseInt(aid));
		accesscontrol.setIp(ip);
		accesscontrol.setPort(Integer.parseInt(port));
		accesscontrol.setAccessType(Short.parseShort(accessType));
		String json = "";
		String addMsg = accessService.save(accesscontrol);
		if(addMsg == "Success"){
			json = "{\"success\":true,\"msg\":\"操作成功！\"}";
		}
		else if(addMsg == "Failure"){
			json = "{\"success\":false,\"msg\":\"操作失败！\"}";
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
