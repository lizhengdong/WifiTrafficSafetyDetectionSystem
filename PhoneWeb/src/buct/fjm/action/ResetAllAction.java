package buct.fjm.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

import buct.fjm.file.FileOperator;

@SuppressWarnings("serial")
@Component("resetAll")
@Scope("prototype")
public class ResetAllAction extends ActionSupport {
	public String execute()throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		String json = "";
		FileOperator fo = new FileOperator();
		try {
			fo.initCopyPath("/usr/local/raywork/init/");
			boolean isCopyH = fo.copyFile("hostapd.conf", "/etc/hostapd/");
			boolean isCopyD = fo.copyFile("dhcpd.conf", "/etc/dhcp/");
			if(isCopyH && isCopyD){
				json = "{success:" + true + "}";
			}
			else{
				json = "{success:" + false + "}";
			}
		} catch (Exception e) {
			// TODO: handle exception
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