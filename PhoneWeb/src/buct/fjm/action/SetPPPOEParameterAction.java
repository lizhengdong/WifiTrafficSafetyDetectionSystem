package buct.fjm.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import buct.fjm.file.FileOperator;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Component("setPPPOEParameter")
@Scope("prototype")
public class SetPPPOEParameterAction extends ActionSupport{
	private String onboot;
	private String username;
	private String password;
	
	public String getOnboot() {
		return onboot;
	}
	public void setOnboot(String onboot) {
		this.onboot = onboot;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String execute() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		String json = "";
		
		FileOperator fo = new FileOperator();
		FileOperator fo2 = new FileOperator();
		
		fo.setDir("/etc/sysconfig/network-scripts/ifcfg-ppp0");
		fo2.setDir("/etc/ppp/chap-secrets");
		
		try {
			fo.setKeyValue("ONBOOT", onboot);
			fo.setKeyValue("USER", username);

			fo2.setKeyValueWithTab(username, password);
			json = "{\"success\":true}";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
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