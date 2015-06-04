package buct.fjm.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import buct.fjm.model.Lpassword;
import buct.fjm.service.PasswordService;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Component ("login")
@Scope ("prototype")
public class LoginAction extends ActionSupport{

	private String userName=null;
	private String password=null;
	private PasswordService passwordService;
	public String execute() throws Exception
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();	
		this.clearErrorsAndMessages();//清空消息
		
		Lpassword dbPw = passwordService.findById(1);
		String dbHash = dbPw.getLoginPw();
		System.out.println(dbHash);
		String pwHash = Md5GenerateandIdentify.generatePassword(password);
		System.out.println(password);
		System.out.println(pwHash);
		if(userName!=null&&userName.equals("admin")&&pwHash.equals(dbHash)){
			session.setAttribute("userName", userName);
			session.setAttribute("isLogin", "Y");
			return "success";
		}
		
		this.addActionMessage("用户名或密码错误！");
		return "failure";
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public PasswordService getPasswordService() {
		return passwordService;
	}

	@Resource
	public void setPasswordService(PasswordService passwordService) {
		this.passwordService = passwordService;
	}
}
