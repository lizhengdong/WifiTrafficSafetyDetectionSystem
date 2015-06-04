package buct.fjm.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Component("stopPPPOE")
@Scope("prototype")
public class StopPPPOEAction extends ActionSupport{
	public String execute() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		String json = "";
		
		try{
			Runtime.getRuntime().exec("/sbin/pppoe-stop");
			//Runtime.getRuntime().exec("service dhcpd start");
			json = "{success:" + true + "}";
		}
		catch(Exception e){
			e.printStackTrace();
			json = "{success:" + false + "}";
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