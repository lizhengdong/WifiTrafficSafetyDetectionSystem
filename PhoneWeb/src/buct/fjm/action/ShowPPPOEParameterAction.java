package buct.fjm.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import buct.fjm.jsonConver.ConvertObjectToJson;
import buct.fjm.model.PPPOEParameter;
import buct.fjm.util.ConfigFind;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Component("showPPPOEParameter")
@Scope("prototype")
public class ShowPPPOEParameterAction extends ActionSupport{
	public String execute() throws Exception{
		//HttpServletRequest request = ServletActionContext.getRequest();
		List<String> pppoeKeys = new ArrayList<String>();
		pppoeKeys.add("ONBOOT");
		pppoeKeys.add("ETH");
		pppoeKeys.add("USER");
		
		Map<String,String> pMap = ConfigFind.find("/etc/sysconfig/network-scripts/ifcfg-ppp0", pppoeKeys);
		String pw = ConfigFind.findPassword("/etc/ppp/chap-secrets", pMap.get("USER"));
		PPPOEParameter pppoeParameter = new PPPOEParameter();
		pppoeParameter.setOnboot(pMap.get("ONBOOT"));
		pppoeParameter.setDevice(pMap.get("ETH"));
		pppoeParameter.setUsername(pMap.get("USER"));
		pppoeParameter.setPassword(pw);
		
		String json = "";
		json =  "{success:true,data:" + ConvertObjectToJson.convertToJson(pppoeParameter) + "}";
		System.out.print(json);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/heml;charset=utf-8");
		response.getWriter().write(json);
		return null;
		
	}
}