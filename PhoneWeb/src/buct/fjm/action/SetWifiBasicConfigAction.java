package buct.fjm.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ActionSupport;

import buct.fjm.file.FileOperator;

@SuppressWarnings("serial")
@Component("setWifiBasicConfig")
@Scope("prototype")
public class SetWifiBasicConfigAction extends ActionSupport{
	private String ssid;
	private String channel;
	private String mode;
//	private String bandwidth;
//	private boolean openwifi;
	private boolean broadcast;
	private String wifipw;
	private FileOperator fo;
	public String getSsid() {
		return ssid;
	}
	public void setSsid(String ssid) {
		this.ssid = ssid;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
//	public String getBandwidth() {
//		return bandwidth;
//	}
//	public void setBandwidth(String bandwidth) {
//		this.bandwidth = bandwidth;
//	}
//	public boolean isOpenwifi() {
//		return openwifi;
//	}
//	public void setOpenwifi(boolean openwifi) {
//		this.openwifi = openwifi;
//	}
	public boolean isBroadcast() {
		return broadcast;
	}
	public void setBroadcast(boolean broadcast) {
		this.broadcast = broadcast;
	}
	
	public String getWifipw() {
		return wifipw;
	}
	public void setWifipw(String wifipw) {
		this.wifipw = wifipw;
	}
	public String execute() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		String json = "";
		fo = new FileOperator();
		fo.setDir("/etc/hostapd/hostapd.conf");
		try {
			fo.setKeyValue("ssid", ssid);
			fo.setKeyValue("wpa_passphrase", wifipw);
			if (channel != "自动") {
				fo.setKeyValue("channel", channel);
			}
			if (mode.equals("11a")) {
				fo.setKeyValue("hw_mode", "a");
			} else if (mode.equals("11b")) {
				fo.setKeyValue("hw_mode", "b");
			} else if (mode.equals("11g")) {
				fo.setKeyValue("hw_mode", "g");
			}
			//		if(bandwidth != "自动"){
			//			fo.setKeyValue("bandwidth", bandwidth);
			//		}
			//System.out.print(broadcast);
			if (broadcast) {
				fo.setKeyValue("ignore_broadcast_ssid", "0");
				//System.out.println("Entered");
			} else {
				fo.setKeyValue("ignore_broadcast_ssid", "1");
				//System.out.println("Entered");
			}
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