package buct.fjm.dao;
import java.util.List;

import buct.fjm.model.Accesscontrol;
@SuppressWarnings("unchecked")
public interface AccesscontrolDAO  {
	public void save(Accesscontrol transientInstance) ;
	public void delete(Accesscontrol persistentInstance) ;
	public Accesscontrol findById(java.lang.Integer id) ;
	public List findByExample(Accesscontrol instance) ;
	public List findByProperty(String propertyName, Object value) ;
	public List findByIp(Object ip) ;
	public List findByPort(Object port) ;
	public List findByAccessType(Object accessType) ;
	public List findAll() ;
	public Accesscontrol merge(Accesscontrol detachedInstance) ;
	public void attachDirty(Accesscontrol instance) ;
	public void attachClean(Accesscontrol instance) ;
}