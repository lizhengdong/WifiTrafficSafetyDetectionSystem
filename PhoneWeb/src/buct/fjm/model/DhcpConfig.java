package buct.fjm.model;

public class DhcpConfig {
	private String startIp;
	private String endIp;
	private String defaultValidTime;
	private String maxValidTime;
	private String gateWay;
	private String defaultHost;
	private String dnsServer;
	
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
	
}