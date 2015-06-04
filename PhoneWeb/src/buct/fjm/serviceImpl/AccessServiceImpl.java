package buct.fjm.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import buct.fjm.dao.AccesscontrolDAO;
import buct.fjm.model.Accesscontrol;
import buct.fjm.service.AccessService;
@Component("accessService")
@SuppressWarnings("unchecked")
public class AccessServiceImpl implements AccessService {

	private AccesscontrolDAO accesscontrolDAO;
	public List<Accesscontrol> findAll() {
		// TODO Auto-generated method stub
		return accesscontrolDAO.findAll();
	}
	public AccesscontrolDAO getAccesscontrolDAO() {
		return accesscontrolDAO;
	}
	@Resource
	public void setAccesscontrolDAO(AccesscontrolDAO accesscontrolDAO) {
		this.accesscontrolDAO = accesscontrolDAO;
	}
	public String save(Accesscontrol accesscontrol) {
		// TODO Auto-generated method stub
		try{
			accesscontrolDAO.save(accesscontrol);
			return "Success";
		}
		catch(Exception e){
			return "Failure";
		}
	}
	public String delete(Accesscontrol persistentInstance) {
		try{
			accesscontrolDAO.delete(persistentInstance);
			return "Success";
		}
		catch(Exception e)
		{
			return "Failure";
		}
	}
	public String update(Accesscontrol instance){
		try{
			accesscontrolDAO.attachDirty(instance);
			return "Success";
		}
		catch(Exception e) 
		{
			return "Failure";
		}
	}
}
