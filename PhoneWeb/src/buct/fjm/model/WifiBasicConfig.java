package buct.fjm.model;

public class WifiBasicConfig {
	private String ssid;
	private String channel;
	private String mode;
	private boolean broadcast;
	private String wifipw;
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
	

}