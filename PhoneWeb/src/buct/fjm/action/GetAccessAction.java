package buct.fjm.action;

import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import buct.fjm.jsonConver.ConverAccessListToJson;
import buct.fjm.model.Accesscontrol;
import buct.fjm.service.AccessService;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Component ("getAccess")
@Scope ("prototype")
public class GetAccessAction extends ActionSupport{

	private AccessService accessService;
	
	
	public AccessService getAccessService() {
		return accessService;
	}

	@Resource
	public void setAccessService(AccessService accessService) {
		this.accessService = accessService;
	}


	public String execute() throws Exception
	{
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		String json ="";
		
		List<Accesscontrol> list=accessService.findAll();
		
		json = ConverAccessListToJson.ConverListToPageJson(list, 100);
		
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
