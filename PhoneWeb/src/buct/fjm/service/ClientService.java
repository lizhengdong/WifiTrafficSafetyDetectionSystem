package buct.fjm.service;

import java.util.List;

import buct.fjm.model.Clientlist;

@SuppressWarnings("unchecked")
public interface ClientService {
	public List<Clientlist> findAll();
	public List queryMac(String queryString);
	public String update(Clientlist instance);
	public String add(Clientlist transientInstance);
	public String delete(Clientlist persistentInstance);

}
