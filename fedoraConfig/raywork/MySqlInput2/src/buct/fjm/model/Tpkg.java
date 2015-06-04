package buct.fjm.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Tpkg entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tpkg", catalog = "phonedbv2")
public class Tpkg implements java.io.Serializable {

	// Fields

	private Integer tpackageId;
	private String tnativeMac;
	private String tremoteIp;
	private Integer tremotePort;
	private Short tprotocolType;
	private Boolean tflowDirection;
	private String tflowAmount;
	private Timestamp tfirstVisitTime;
	private Timestamp tlastVisitTime;
	private Set<Tpkguri> tpkguris = new HashSet(0);

	// Constructors

	/** default constructor */
	public Tpkg() {
	}

	/** full constructor */
	public Tpkg(String tnativeMac, String tremoteIp, Integer tremotePort,
			Short tprotocolType, Boolean tflowDirection, String tflowAmount,
			Timestamp tfirstVisitTime, Timestamp tlastVisitTime, Set tpkguris) {
		this.tnativeMac = tnativeMac;
		this.tremoteIp = tremoteIp;
		this.tremotePort = tremotePort;
		this.tprotocolType = tprotocolType;
		this.tflowDirection = tflowDirection;
		this.tflowAmount = tflowAmount;
		this.tfirstVisitTime = tfirstVisitTime;
		this.tlastVisitTime = tlastVisitTime;
		this.tpkguris = tpkguris;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "tPackageId", unique = true, nullable = false)
	public Integer getTpackageId() {
		return this.tpackageId;
	}

	public void setTpackageId(Integer tpackageId) {
		this.tpackageId = tpackageId;
	}
	
	@Column(name = "tNativeMac", length = 20)
	public String getTnativeMac() {
		return this.tnativeMac;
	}

	public void setTnativeMac(String tnativeMac) {
		this.tnativeMac = tnativeMac;
	}
	
	@Column(name = "tRemoteIp", length = 20)
	public String getTremoteIp() {
		return this.tremoteIp;
	}

	public void setTremoteIp(String tremoteIp) {
		this.tremoteIp = tremoteIp;
	}

	@Column(name = "tRemotePort")
	public Integer getTremotePort() {
		return this.tremotePort;
	}

	public void setTremotePort(Integer tremotePort) {
		this.tremotePort = tremotePort;
	}

	@Column(name = "tProtocolType")
	public Short getTprotocolType() {
		return this.tprotocolType;
	}

	public void setTprotocolType(Short tprotocolType) {
		this.tprotocolType = tprotocolType;
	}

	@Column(name = "tFlowDirection")
	public Boolean getTflowDirection() {
		return this.tflowDirection;
	}

	public void setTflowDirection(Boolean tflowDirection) {
		this.tflowDirection = tflowDirection;
	}

	@Column(name = "tFlowAmount", length = 20)
	public String getTflowAmount() {
		return this.tflowAmount;
	}

	public void setTflowAmount(String tflowAmount) {
		this.tflowAmount = tflowAmount;
	}

	@Column(name = "tFirstVisitTime")
	public Timestamp getTfirstVisitTime() {
		return this.tfirstVisitTime;
	}

	public void setTfirstVisitTime(Timestamp tfirstVisitTime) {
		this.tfirstVisitTime = tfirstVisitTime;
	}

	@Column(name = "tLastVisitTime")
	public Timestamp getTlastVisitTime() {
		return this.tlastVisitTime;
	}

	public void setTlastVisitTime(Timestamp tlastVisitTime) {
		this.tlastVisitTime = tlastVisitTime;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "tpkg")
	public Set<Tpkguri> getTpkguris() {
		return this.tpkguris;
	}

	public void setTpkguris(Set<Tpkguri> tpkguris) {
		this.tpkguris = tpkguris;
	}

}