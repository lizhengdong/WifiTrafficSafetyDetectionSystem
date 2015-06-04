package buct.fjm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Tpkguri entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tpkguri", catalog = "phonedbv2")
public class Tpkguri implements java.io.Serializable {

	// Fields

	private Integer tid;
	private Tpkg tpkg;
	private Uri uri;

	// Constructors

	/** default constructor */
	public Tpkguri() {
	}

	/** full constructor */
	public Tpkguri(Tpkg tpkg, Uri uri) {
		this.tpkg = tpkg;
		this.uri = uri;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "tId", unique = true, nullable = false)
	public Integer getTid() {
		return this.tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tPackageId")
	public Tpkg getTpkg() {
		return this.tpkg;
	}

	public void setTpkg(Tpkg tpkg) {
		this.tpkg = tpkg;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "uriId")
	public Uri getUri() {
		return this.uri;
	}

	public void setUri(Uri uri) {
		this.uri = uri;
	}

}