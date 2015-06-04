package buct.fjm.action;

import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import buct.fjm.service.PasswordService;

import com.opensymphony.xwork2.ActionSupport;
import buct.fjm.model.Lpassword;

@SuppressWarnings("serial")
@Component("identifyPassword")
@Scope("prototype")
public class IdentifyPasswordAction extends ActionSupport{
	private String loginPw;
	private PasswordService passwordService;
	private Md5GenerateandIdentify md5GaI;
	public PasswordService getPasswordService(){
		return passwordService;
	}
	@Resource
	public void setPasswordService(PasswordService passwordService){
		this.passwordService = passwordService;
	}
	public String getLoginPw() {
		return loginPw;
	}
	public void setLoginPw(String loginPw) {
		this.loginPw = loginPw;
	}
	
	public String execute() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		String json = "";
		Lpassword dbPw = passwordService.findById(1);
		String dbHash = dbPw.getLoginPw();
		//Password oldPw = new Password();
		//String oldHash = md5GaI.generatePassword(loginPw);
		boolean isSame = md5GaI.identifyPassword(dbHash, loginPw);
		if(isSame){
			json = "{\"success\":true,\"msg\":\"操作成功！\"}";
		}
		else {
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
