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
import buct.fjm.model.DhcpConfig;
import buct.fjm.util.ConfigFindDhcp;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Component("showDhcpConfig")
@Scope("prototype")
public class ShowDhcpConfigAction {
	public String execute() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		
		DhcpConfig dConfig = new DhcpConfig();
		
		List<String> dhcpConfigKeys = new ArrayList<String>();
		//dhcpConfigKeys.add("sIp");
		//dhcpConfigKeys.add("eIp");
		dhcpConfigKeys.add("	default-lease-time");
		dhcpConfigKeys.add("	max-lease-time");
		dhcpConfigKeys.add("routers");
		dhcpConfigKeys.add("domain-name-servers");
		Map<String, String> dMap = ConfigFindDhcp.findDhcp("/etc/dhcp/dhcpd.conf", dhcpConfigKeys);
		
		dConfig.setStartIp(dMap.get("startIp"));
		dConfig.setEndIp(dMap.get("endIp"));
		dConfig.setDefaultValidTime(dMap.get("	default-lease-time"));
		dConfig.setMaxValidTime(dMap.get("	max-lease-time"));
		dConfig.setGateWay(dMap.get("routers"));
		dConfig.setDefaultHost(dMap.get("routers"));
		dConfig.setDnsServer(dMap.get("domain-name-servers"));
		String json="";
		json = "{success:true,data:"+ConvertObjectToJson.convertToJson(dConfig)+"}";
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(json);
		return null;
	}

}