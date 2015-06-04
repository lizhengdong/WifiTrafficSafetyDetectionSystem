package buct.fjm.service;

import java.util.List;

import buct.fjm.model.Accesscontrol;
@SuppressWarnings("unchecked")
public interface AccessService {
	public List<Accesscontrol> findAll() ;
	public String save(Accesscontrol accesscontrol);
	public String delete(Accesscontrol persistentInstance);
	public String update(Accesscontrol instance);
}
