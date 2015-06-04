package buct.fjm.dao;
import java.util.List;

import buct.fjm.model.Tpkguri;
@SuppressWarnings("unchecked")
public interface TpkguriDAO  {
	public void save(Tpkguri transientInstance) ;
	public void delete(Tpkguri persistentInstance) ;
	public Tpkguri findById(java.lang.Integer id) ;
	public List findByExample(Tpkguri instance) ;
	public List findByProperty(String propertyName, Object value) ;
	public List findAll() ;
	public Tpkguri merge(Tpkguri detachedInstance) ;
	public void attachDirty(Tpkguri instance) ;
	public void attachClean(Tpkguri instance) ;
}