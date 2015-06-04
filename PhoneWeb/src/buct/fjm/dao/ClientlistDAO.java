package buct.fjm.dao;

import java.util.List;

import buct.fjm.model.Clientlist;

@SuppressWarnings("unchecked")
public interface ClientlistDAO {

	public List findAll();
	public List queryMac(String queryString);
	public void add(Clientlist transientInstance);
	public void delete(Clientlist persistentInstance);
	public void attachDirty(Clientlist instance);
}
