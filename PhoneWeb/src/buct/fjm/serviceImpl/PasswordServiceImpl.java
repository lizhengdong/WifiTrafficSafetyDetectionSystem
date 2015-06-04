package buct.fjm.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import buct.fjm.dao.PasswordDAO;
import buct.fjm.model.Lpassword;
import buct.fjm.service.PasswordService;

@Component("passwordService")
@SuppressWarnings("unchecked")
public class PasswordServiceImpl implements PasswordService{
	private PasswordDAO passwordDAO;
	public PasswordDAO getPasswordDAO(){
		return passwordDAO;
	}
	public List<Lpassword> findAll(){
		return passwordDAO.findAll();
	}
	public Lpassword findById(java.lang.Integer id){
		return passwordDAO.findById(id);
	}
	@Resource
	public void setPasswordDAO(PasswordDAO passwordDAO){
		this.passwordDAO = passwordDAO;
	}
	public String update(Lpassword instance){
		try{
			passwordDAO.attachDirty(instance);
			return "Success";
		}
		catch(Exception e){
			return "Failure";
		}
	}

}
