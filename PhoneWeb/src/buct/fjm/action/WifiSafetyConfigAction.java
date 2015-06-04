package buct.fjm.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import buct.fjm.hostapdreplace.WifiSafetyConfigToHostapd;
import buct.fjm.runShell.RunLinuxShell;
import buct.fjm.util.ConfigEdit;
import buct.fjm.util.ConfigWrite;
import com.opensymphony.xwork2.ActionSupport;

//wifiSafetyConfig
@SuppressWarnings("serial")
@Component("wifiSafetyConfig")
@Scope("prototype")
public class WifiSafetyConfigAction extends ActionSupport {
	private String safetyConfig;
	private String rzType1;
	private String jmsf1;
	private String pskPwd;

	/*
	 * private String zmygxzq1; private String rzlx2; private String jmsf2;
	 * private String radiusServerIP; private String radiusPort; private String
	 * radiusPwd; private String zmygxzq2;
	 */
	public String execute() throws Exception {
		Map<String, String> changes = new HashMap<String, String>();

		if (safetyConfig.equals("radio1")) {
			changes.put("safetyConfig1", "");
		} else if (safetyConfig.equals("radio2")) {
			changes.put("safetyConfig2", "WPA-PSK/WPA2-PSK");
			changes.put("rzType1", rzType1);
			changes.put("jmsf1", jmsf1);
			changes.put("pskPwd", pskPwd);
			// changes.put("zmygxzq1", zmygxzq1);

		}/*
		 * else if (safetyConfig.equals("radio3")) { changes.put("safetyConfig",
		 * "WPA/WPA2"); changes.put("rzlx2", rzlx2); changes.put("jmsf2",
		 * jmsf2); changes.put("radiusServerIP", radiusServerIP);
		 * changes.put("radiusPort", radiusPort); changes.put("radiusPwd",
		 * radiusPwd); changes.put("zmygxzq2", zmygxzq2); }
		 */
		String json = "";
		try {
			// ConfigEdit.edit(
			// "C://Workspaces//MyEclipse 10//wifiSafetyConfig.conf",
			// changes);
			String filePath = "/usr/phoneWebConfig/wifiSafetyConfig.conf";
			ConfigWrite.write(filePath, changes);
			// 将临时配置文件的配置项替换到Hostapd.conf中
			String hostapdConfigPath = "/etc/hostapd/hostapd.conf";
			WifiSafetyConfigToHostapd.replace(filePath, hostapdConfigPath);
			json = "{\"success\":true,\"msg\":\"操作成功！\"}";

			// 操作成功，重启服务
			try {
				RunLinuxShell.run();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			json = "{\"success\":true,\"msg\":\"操作失败！\"}";
			e.printStackTrace();
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
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

	public String getSafetyConfig() {
		return safetyConfig;
	}

	public void setSafetyConfig(String safetyConfig) {
		this.safetyConfig = safetyConfig;
	}

	public String getRzType1() {
		return rzType1;
	}

	public void setRzType1(String rzType1) {
		this.rzType1 = rzType1;
	}

	public String getJmsf1() {
		return jmsf1;
	}

	public void setJmsf1(String jmsf1) {
		this.jmsf1 = jmsf1;
	}

	public String getPskPwd() {
		return pskPwd;
	}

	public void setPskPwd(String pskPwd) {
		this.pskPwd = pskPwd;
	}
	/*
	 * public String getZmygxzq1() { return zmygxzq1; }
	 * 
	 * public void setZmygxzq1(String zmygxzq1) { this.zmygxzq1 = zmygxzq1; }
	 * 
	 * public String getRzlx2() { return rzlx2; }
	 * 
	 * public void setRzlx2(String rzlx2) { this.rzlx2 = rzlx2; }
	 * 
	 * public String getJmsf2() { return jmsf2; }
	 * 
	 * public void setJmsf2(String jmsf2) { this.jmsf2 = jmsf2; }
	 * 
	 * public String getRadiusServerIP() { return radiusServerIP; }
	 * 
	 * public void setRadiusServerIP(String radiusServerIP) {
	 * this.radiusServerIP = radiusServerIP; }
	 * 
	 * public String getRadiusPort() { return radiusPort; }
	 * 
	 * public void setRadiusPort(String radiusPort) { this.radiusPort =
	 * radiusPort; }
	 * 
	 * public String getRadiusPwd() { return radiusPwd; }
	 * 
	 * public void setRadiusPwd(String radiusPwd) { this.radiusPwd = radiusPwd;
	 * }
	 * 
	 * public String getZmygxzq2() { return zmygxzq2; }
	 * 
	 * public void setZmygxzq2(String zmygxzq2) { this.zmygxzq2 = zmygxzq2; }
	 */
}
