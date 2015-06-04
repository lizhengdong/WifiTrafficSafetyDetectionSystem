package buct.fjm.jsonConver;

import java.sql.Timestamp;

import buct.fjm.model.Pkg;

public class PkgDetor {
	private Integer packageId;
	private String nativeMac;
	private String remoteIp;
	private Integer remotePort;
	private Short protocolType;
	private Boolean flowDirectioin;
	private String flowAmount;
	private Timestamp firstVisitTime;
	private Timestamp lastVisitTime;
	private Timestamp dateVisit;
	private int type;
	
	public PkgDetor(Pkg pkg,int type){
		this.packageId=pkg.getPackageId();
		this.nativeMac = pkg.getNativeMac();
		this.remoteIp = pkg.getRemoteIp();
		this.remotePort = pkg.getRemotePort();
		this.protocolType = pkg.getProtocolType();
		this.flowDirectioin = pkg.getFlowDirectioin();
		this.flowAmount = pkg.getFlowAmount();
		this.firstVisitTime = pkg.getFirstVisitTime();
		this.lastVisitTime = pkg.getLastVisitTime();
		this.dateVisit = pkg.getDateVisit();
		this.type=type;
	}

	public Integer getPackageId() {
		return packageId;
	}

	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}

	public String getNativeMac() {
		return nativeMac;
	}

	public void setNativeMac(String nativeMac) {
		this.nativeMac = nativeMac;
	}

	public String getRemoteIp() {
		return remoteIp;
	}

	public void setRemoteIp(String remoteIp) {
		this.remoteIp = remoteIp;
	}

	public Integer getRemotePort() {
		return remotePort;
	}

	public void setRemotePort(Integer remotePort) {
		this.remotePort = remotePort;
	}

	public Short getProtocolType() {
		return protocolType;
	}

	public void setProtocolType(Short protocolType) {
		this.protocolType = protocolType;
	}

	public Boolean getFlowDirectioin() {
		return flowDirectioin;
	}

	public void setFlowDirectioin(Boolean flowDirectioin) {
		this.flowDirectioin = flowDirectioin;
	}

	public String getFlowAmount() {
		return flowAmount;
	}

	public void setFlowAmount(String flowAmount) {
		this.flowAmount = flowAmount;
	}

	public Timestamp getFirstVisitTime() {
		return firstVisitTime;
	}

	public void setFirstVisitTime(Timestamp firstVisitTime) {
		this.firstVisitTime = firstVisitTime;
	}

	public Timestamp getLastVisitTime() {
		return lastVisitTime;
	}

	public void setLastVisitTime(Timestamp lastVisitTime) {
		this.lastVisitTime = lastVisitTime;
	}

	public Timestamp getDateVisit() {
		return dateVisit;
	}

	public void setDateVisit(Timestamp dateVisit) {
		this.dateVisit = dateVisit;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	
}
