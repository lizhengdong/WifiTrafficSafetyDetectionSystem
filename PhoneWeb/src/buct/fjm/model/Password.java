package buct.fjm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Password", catalog = "phonedbv2")
public class Password implements java.io.Serializable{
	//Fields
	private Integer pId;
	private String loginPw;
	//Constructors
	//Default constructor
	public Password(){
		
	}
	//Full constructor
	public Password(String loginPw){
		this.loginPw = loginPw;
	}
	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "pId", length = 2,unique = true, nullable = false)
	public Integer getPid(){
		return pId;
	}
	public void setPid(Integer pId){
		this.pId = pId;
	}
	@Column(name = "loginPw", length = 32)
	public String getLoginPw(){
		return this.loginPw;
	}
	public void setLoginPw(String loginPw){
		this.loginPw = loginPw;
	}

}
