package buct.fjm.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import buct.fjm.ipAnalyse.IpAddress;
import buct.fjm.ipAnalyse.SearchIp;
import buct.fjm.jsonConver.ConvertObjectToJson;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Component ("getAddress")
@Scope ("prototype")
public class GetAddressAction extends ActionSupport{
	private String pkgId=null;
	
	
	public String execute() throws Exception
	{
		IpAddress ipAdd=SearchIp.search(pkgId);
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		String json ="";
		json=ConvertObjectToJson.convertToJson(ipAdd);
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


	public String getPkgId() {
		return pkgId;
	}


	public void setPkgId(String pkgId) {
		this.pkgId = pkgId;
	}
	
}
