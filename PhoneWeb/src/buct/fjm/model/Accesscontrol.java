package buct.fjm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Accesscontrol entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "accesscontrol", catalog = "phonedbv2")
public class Accesscontrol implements java.io.Serializable {

	// Fields

	private Integer aid;
	private String ip;
	private Integer port;
	private Short accessType;

	// Constructors

	/** default constructor */
	public Accesscontrol() {
	}

	/** full constructor */
	public Accesscontrol(String ip, Integer port, Short accessType) {
		this.ip = ip;
		this.port = port;
		this.accessType = accessType;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "aId", unique = true, nullable = false)
	public Integer getAid() {
		return this.aid;
	}

	public void setAid(Integer aid) {
		this.aid = aid;
	}
	
	@Column(name = "ip", length = 20)
	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	@Column(name = "port")
	public Integer getPort() {
		return this.port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}
	
	@Column(name = "accessType")
	public Short getAccessType() {
		return this.accessType;
	}

	public void setAccessType(Short accessType) {
		this.accessType = accessType;
	}

}