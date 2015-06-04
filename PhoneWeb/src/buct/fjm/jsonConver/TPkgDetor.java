package buct.fjm.jsonConver;

import java.sql.Timestamp;

import buct.fjm.model.Tpkg;

public class TPkgDetor {
	private Integer tpackageId;
	private String tnativeMac;
	private String tremoteIp;
	private Integer tremotePort;
	private Short tprotocolType;
	private Boolean tflowDirection;
	private String tflowAmount;
	private Timestamp tfirstVisitTime;
	private Timestamp tlastVisitTime;
	private int type;
	
	public TPkgDetor(Tpkg tpkg,int type){
		this.tpackageId=tpkg.getTpackageId();
		this.tnativeMac = tpkg.getTnativeMac();
		this.tremoteIp = tpkg.getTremoteIp();
		this.tremotePort = tpkg.getTremotePort();
		this.tprotocolType = tpkg.getTprotocolType();
		this.tflowDirection = tpkg.getTflowDirection();
		this.tflowAmount = tpkg.getTflowAmount();
		this.tfirstVisitTime = tpkg.getTfirstVisitTime();
		this.tlastVisitTime = tpkg.getTlastVisitTime();
		this.type=type;
	}

	

	public Integer getTpackageId() {
		return tpackageId;
	}



	public void setTpackageId(Integer tpackageId) {
		this.tpackageId = tpackageId;
	}



	public String getTnativeMac() {
		return tnativeMac;
	}



	public void setTnativeMac(String tnativeMac) {
		this.tnativeMac = tnativeMac;
	}



	public String getTremoteIp() {
		return tremoteIp;
	}



	public void setTremoteIp(String tremoteIp) {
		this.tremoteIp = tremoteIp;
	}



	public Integer getTremotePort() {
		return tremotePort;
	}



	public void setTremotePort(Integer tremotePort) {
		this.tremotePort = tremotePort;
	}



	public Short getTprotocolType() {
		return tprotocolType;
	}



	public void setTprotocolType(Short tprotocolType) {
		this.tprotocolType = tprotocolType;
	}



	public Boolean getTflowDirection() {
		return tflowDirection;
	}



	public void setTflowDirection(Boolean tflowDirection) {
		this.tflowDirection = tflowDirection;
	}



	public String getTflowAmount() {
		return tflowAmount;
	}



	public void setTflowAmount(String tflowAmount) {
		this.tflowAmount = tflowAmount;
	}



	public Timestamp getTfirstVisitTime() {
		return tfirstVisitTime;
	}



	public void setTfirstVisitTime(Timestamp tfirstVisitTime) {
		this.tfirstVisitTime = tfirstVisitTime;
	}



	public Timestamp getTlastVisitTime() {
		return tlastVisitTime;
	}



	public void setTlastVisitTime(Timestamp tlastVisitTime) {
		this.tlastVisitTime = tlastVisitTime;
	}



	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	
}
