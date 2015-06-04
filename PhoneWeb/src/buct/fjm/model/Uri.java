package buct.fjm.model;

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
 * Uri entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "uri", catalog = "phonedbv2")
public class Uri implements java.io.Serializable {

	// Fields

	private Integer uriId;
	private String host;
	private String path;
	private Integer port;
	private Set<Tpkguri> tpkguris = new HashSet(0);
	private Set<Pkguri> pkguris = new HashSet(0);

	// Constructors

	/** default constructor */
	public Uri() {
	}

	/** full constructor */
	public Uri(String host, String path, Integer port, Set tpkguris, Set pkguris) {
		this.host = host;
		this.path = path;
		this.port = port;
		this.tpkguris = tpkguris;
		this.pkguris = pkguris;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "uriId", unique = true, nullable = false)
	public Integer getUriId() {
		return this.uriId;
	}

	public void setUriId(Integer uriId) {
		this.uriId = uriId;
	}
	
	@Column(name = "host", length = 50)
	public String getHost() {
		return this.host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	@Column(name = "path", length = 5000)
	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Column(name = "port")
	public Integer getPort() {
		return this.port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "uri")
	public Set<Tpkguri> getTpkguris() {
		return this.tpkguris;
	}

	public void setTpkguris(Set<Tpkguri> tpkguris) {
		this.tpkguris = tpkguris;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "uri")
	public Set<Pkguri> getPkguris() {
		return this.pkguris;
	}

	public void setPkguris(Set<Pkguri> pkguris) {
		this.pkguris = pkguris;
	}

}