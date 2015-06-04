package buct.fjm.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import buct.fjm.dao.ClientlistDAO;
import buct.fjm.model.Clientlist;
import buct.fjm.service.ClientService;

@Component("clientService")
@SuppressWarnings("unchecked")
public class ClientServiceImpl implements ClientService{
	private ClientlistDAO clientlistDao;

	public ClientlistDAO getClientlistDao() {
		return clientlistDao;
	}

	@Resource
	public void setClientlistDao(ClientlistDAO clientlistDao) {
		this.clientlistDao = clientlistDao;
	}
	
	public List<Clientlist> findAll(){
		return clientlistDao.findAll();
	}
	
	public List queryMac(String queryString){
		return clientlistDao.queryMac(queryString);
	}
	
	public String update(Clientlist instance){
		try {
			clientlistDao.attachDirty(instance);
			return "Success";
		}
		catch(Exception e){
			return "Failure";
		}
	}
	
	public String add(Clientlist transientInstance){
		try {
			clientlistDao.add(transientInstance);
			return "Success";
		}
		catch(Exception e){
			return "Failure";
		}
	}
	
	public String delete(Clientlist persistentInstance){
		try {
			clientlistDao.delete(persistentInstance);
			return "Success";
		}
		catch(Exception e){
			return "Failure";
		}
	}



}
