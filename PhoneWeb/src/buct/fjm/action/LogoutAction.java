package buct.fjm.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Component ("logout")
@Scope ("prototype")
public class LogoutAction extends ActionSupport {
	public String execute() throws Exception
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();	
		this.clearErrorsAndMessages();//清空消息
		
			session.removeAttribute("userName");
			session.removeAttribute("isLogin");
			return "success";
	
	}
}
