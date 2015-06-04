package buct.fjm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "clientlist", catalog = "phonedbv2")
public class Clientlist implements java.io.Serializable{
	private Integer cid;
	private String cmac;
	private Boolean showpkgs;
	
	//Default constructor
	public Clientlist(){
		//do noting
	}
	
	//Full constructor
	public Clientlist(String cmac, Boolean showpkgs){
		this.cmac = cmac;
		this.showpkgs = showpkgs;
	}

	@Id
	@GeneratedValue
	@Column(name = "cId", unique = true, nullable = true)
	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	@Column(name = "cMac", length = 20)
	public String getCmac() {
		return cmac;
	}

	public void setCmac(String cmac) {
		this.cmac = cmac;
	}

	@Column(name = "showPkgs")
	public Boolean isShowpkgs() {
		return this.showpkgs;
	}

	public void setShowpkgs(Boolean showpkgs) {
		this.showpkgs = showpkgs;
	}
	
	

}
