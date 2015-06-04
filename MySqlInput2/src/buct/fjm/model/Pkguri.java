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
 * Pkguri entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "pkguri", catalog = "phonedbv2")
public class Pkguri implements java.io.Serializable {

	// Fields

	private Integer id;
	private Pkg pkg;
	private Uri uri;

	// Constructors

	/** default constructor */
	public Pkguri() {
	}

	/** full constructor */
	public Pkguri(Pkg pkg, Uri uri) {
		this.pkg = pkg;
		this.uri = uri;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "packageId")
	public Pkg getPkg() {
		return this.pkg;
	}

	public void setPkg(Pkg pkg) {
		this.pkg = pkg;
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