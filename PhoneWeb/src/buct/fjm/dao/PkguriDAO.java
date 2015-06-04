package buct.fjm.dao;
import java.util.List;

import buct.fjm.model.Pkguri;
@SuppressWarnings("unchecked")
public interface PkguriDAO  {
	public int getCount(String hql);
	public List<Pkguri> queryListObjectAllForPage(String queryString,int pageSize,int page);
	public void save(Pkguri transientInstance) ;
	public List getList(String hql);
	public void delete(Pkguri persistentInstance) ;
	public Pkguri findById(java.lang.Integer id) ;
	public List findByExample(Pkguri instance) ;
	public List findByProperty(String propertyName, Object value) ;
	public List findAll() ;
	public Pkguri merge(Pkguri detachedInstance) ;
	public void attachDirty(Pkguri instance) ;
	public void attachClean(Pkguri instance) ;
}