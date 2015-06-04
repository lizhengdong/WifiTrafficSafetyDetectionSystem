package buct.fjm.service;

import java.util.List;

import buct.fjm.model.Tpkg;

@SuppressWarnings("unchecked")
public interface TPkgService {
	
	public int getCount(String hql);
	public List<Tpkg> getListBySql(String hql,int page,int pageSize);
	public List<Tpkg> getListBySql(String hql);
	public List queryMac(String hql);
	public void save(Tpkg transientInstance) ;
	public void delete(Tpkg persistentInstance) ;
	public Tpkg findById(java.lang.Integer id) ;
	public List<Tpkg> findByExample(Tpkg instance) ;
	public List findByProperty(String propertyName, Object value) ;
	public List<Tpkg> findAll() ;
	public Tpkg merge(Tpkg detachedInstance) ;
	public void attachDirty(Tpkg instance) ;
	public void attachClean(Tpkg instance) ;
}