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
 * Pkg entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "pkg", catalog = "phonedbv2")
public class Pkg implements java.io.Serializable {

	// Fields

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
	
	
	private Set<Pkguri> pkguris = new HashSet(0);

	
	
	// Constructors

	/** default constructor */
	public Pkg() {
	}

	/** full constructor */
	public Pkg(String nativeMac, String remoteIp, Integer remotePort,
			Short protocolType, Boolean flowDirectioin, String flowAmount,
			Timestamp firstVisitTime, Timestamp lastVisitTime,
			Timestamp dateVisit, Set pkguris) {
		this.nativeMac = nativeMac;
		this.remoteIp = remoteIp;
		this.remotePort = remotePort;
		this.protocolType = protocolType;
		this.flowDirectioin = flowDirectioin;
		this.flowAmount = flowAmount;
		this.firstVisitTime = firstVisitTime;
		this.lastVisitTime = lastVisitTime;
		this.dateVisit = dateVisit;
		this.pkguris = pkguris;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "packageId", unique = true, nullable = false)
	public Integer getPackageId() {
		return this.packageId;
	}

	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}
	
	@Column(name = "nativeMac", length = 20)
	public String getNativeMac() {
		return this.nativeMac;
	}

	public void setNativeMac(String nativeMac) {
		this.nativeMac = nativeMac;
	}
	
	@Column(name = "remoteIp", length = 20)
	public String getRemoteIp() {
		return this.remoteIp;
	}

	public void setRemoteIp(String remoteIp) {
		this.remoteIp = remoteIp;
	}

	@Column(name = "remotePort")
	public Integer getRemotePort() {
		return this.remotePort;
	}

	public void setRemotePort(Integer remotePort) {
		this.remotePort = remotePort;
	}

	@Column(name = "protocolType")
	public Short getProtocolType() {
		return this.protocolType;
	}

	public void setProtocolType(Short protocolType) {
		this.protocolType = protocolType;
	}

	@Column(name = "flowDirectioin")
	public Boolean getFlowDirectioin() {
		return this.flowDirectioin;
	}

	public void setFlowDirectioin(Boolean flowDirectioin) {
		this.flowDirectioin = flowDirectioin;
	}

	@Column(name = "flowAmount", length = 20)
	public String getFlowAmount() {
		return this.flowAmount;
	}

	public void setFlowAmount(String flowAmount) {
		this.flowAmount = flowAmount;
	}

	@Column(name = "firstVisitTime")
	public Timestamp getFirstVisitTime() {
		return this.firstVisitTime;
	}

	public void setFirstVisitTime(Timestamp firstVisitTime) {
		this.firstVisitTime = firstVisitTime;
	}

	@Column(name = "lastVisitTime")
	public Timestamp getLastVisitTime() {
		return this.lastVisitTime;
	}

	public void setLastVisitTime(Timestamp lastVisitTime) {
		this.lastVisitTime = lastVisitTime;
	}

	@Column(name = "dateVisit")
	public Timestamp getDateVisit() {
		return this.dateVisit;
	}

	public void setDateVisit(Timestamp dateVisit) {
		this.dateVisit = dateVisit;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pkg")
	public Set<Pkguri> getPkguris() {
		return this.pkguris;
	}

	public void setPkguris(Set<Pkguri> pkguris) {
		this.pkguris = pkguris;
	}

}