package buct.fjm.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import buct.fjm.jsonConver.ConvertObjectToJson;
import buct.fjm.model.Config;
import buct.fjm.util.ConfigFind;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Component ("showConfig")
@Scope ("prototype")
public class ShowConfigAction extends ActionSupport{

	public String execute() throws Exception
	{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session=request.getSession();
		
		Config config=new Config();
		
		List<String> systemConfigKeys=new ArrayList<String>();
		systemConfigKeys.add("FilePath");
		systemConfigKeys.add("CopyPath");
		systemConfigKeys.add("IsContinue");
		Map<String,String> sc=ConfigFind.find("E:/Workspaces/PhonePacp/MySqlInput2/SystemConfig.conf", systemConfigKeys);
		
		config.setFilePath(sc.get("FilePath"));
		config.setCopyPath(sc.get("CopyPath"));
		config.setC(Boolean.parseBoolean(sc.get("IsContinue")));
		
		List<String> analyseConfigKeys=new ArrayList<String>();
		analyseConfigKeys.add("NativeMac");
		Map<String,String> ac=ConfigFind.find("E:/Workspaces/PhonePacp/MySqlInput2/AnalyseConfig.conf", analyseConfigKeys);
		config.setNativeMac(ac.get("NativeMac"));
		
		config.setWfpW("dont know");
		
		String json="";
		json = "{success:true,data:"+ConvertObjectToJson.convertToJson(config)+"}";
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(json);
		return null;
	}
}
