package buct.fjm.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

import buct.fjm.file.*;

@SuppressWarnings("serial")
@Component("setDhcp")
@Scope("prototype")
public class SetDhcpAction extends ActionSupport{
	private String dhcpService;
	private String startIp;
	private String endIp;
	private String defaultValidTime;
	private String maxValidTime;
	private String gateWay;
	private String defaultHost;
	private String dnsServer;
	private FileOperatorSpace fos = null;
	public String getDhcpService() {
		return dhcpService;
	}
	public void setDhcpService(String dhcpService) {
		this.dhcpService = dhcpService;
	}
	public String getStartIp() {
		return startIp;
	}
	public void setStartIp(String startIp) {
		this.startIp = startIp;
	}
	public String getEndIp() {
		return endIp;
	}
	public void setEndIp(String endIp) {
		this.endIp = endIp;
	}
	public String getGateWay() {
		return gateWay;
	}
	public void setGateWay(String gateWay) {
		this.gateWay = gateWay;
	}
	public String getDefaultHost() {
		return defaultHost;
	}
	public void setDefaultHost(String defaultHost) {
		this.defaultHost = defaultHost;
	}
	public String getDnsServer() {
		return dnsServer;
	}
	public void setDnsServer(String dnsServer) {
		this.dnsServer = dnsServer;
	}
	
	public String getDefaultValidTime() {
		return defaultValidTime;
	}
	public void setDefaultValidTime(String defaultValidTime) {
		this.defaultValidTime = defaultValidTime;
	}
	public String getMaxValidTime() {
		return maxValidTime;
	}
	public void setMaxValidTime(String maxValidTime) {
		this.maxValidTime = maxValidTime;
	}
	public String execute() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		String json = "";
		//String json = "";
		fos = new FileOperatorSpace();
		fos.setDir("/etc/dhcp/dhcpd.conf");
//		if(dhcpService.equals("Y")){
//			fos.setKeyValue("dhcpService", "true");
//		}
//		else if(dhcpService.equals("N")){
//			fos.setKeyValue("dhcpService", "false");
//		}
		try {
			fos.setRange(startIp, endIp);
			fos.setKeyValue("default-lease-time", defaultValidTime);
			fos.setKeyValue("max-lease-time", maxValidTime);
			fos.setKeyValue("routers", gateWay);
			//fos.setKeyValue("defaultHost", defaultHost);
			fos.setKeyValue("domain-name-servers", dnsServer);
			json = "{\"success\":true}";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			json = "{\"success\":false}";
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