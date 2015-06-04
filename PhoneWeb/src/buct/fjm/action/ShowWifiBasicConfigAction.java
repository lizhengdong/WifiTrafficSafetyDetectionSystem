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
import buct.fjm.model.WifiBasicConfig;
import buct.fjm.util.ConfigFind;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Component("showWifiBasicConfig")
@Scope("prototype")
public class ShowWifiBasicConfigAction extends ActionSupport{
	public String execute() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		
		WifiBasicConfig wbConfig = new WifiBasicConfig();
		
		List<String> wifiConfigKeys = new ArrayList<String>();
		wifiConfigKeys.add("ssid");
		wifiConfigKeys.add("channel");
		wifiConfigKeys.add("hw_mode");
		wifiConfigKeys.add("ignore_broadcast_ssid");
		wifiConfigKeys.add("wpa_passphrase");
		Map<String, String> wMap = ConfigFind.find("/etc/hostapd/hostapd.conf", wifiConfigKeys);
		
		wbConfig.setSsid(wMap.get("ssid"));
		wbConfig.setChannel(wMap.get("channel"));
		wbConfig.setWifipw(wMap.get("wpa_passphrase"));
		if((wMap.get("hw_mode")).equals("a")){
			wbConfig.setMode("11a");
		}
		else if((wMap.get("hw_mode")).equals("b")){
			wbConfig.setMode("11b");
		}
		else if((wMap.get("hw_mode")).equals("g")){
			wbConfig.setMode("11g");
		}
		if((wMap.get("ignore_broadcast_ssid")).equals("0")){
			wbConfig.setBroadcast(true);
		}
		else if((wMap.get("ignore_broadcast_ssid")).equals("1")){
			wbConfig.setBroadcast(false);
		}
		
		String json = "";
		json = "{success:true,data:" + ConvertObjectToJson.convertToJson(wbConfig) + "}";
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(json);
		return null;
	}

}