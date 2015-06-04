package buct.fjm.dao;
import java.util.List;

import buct.fjm.model.Uri;
@SuppressWarnings("unchecked")
public interface UriDAO  {
	public void save(Uri transientInstance) ;
	public void delete(Uri persistentInstance) ;
	public Uri findById(java.lang.Integer id) ;
	public List findByExample(Uri instance) ;
	public List findByProperty(String propertyName, Object value) ;
	public List findByHost(Object host) ;
	public List findByPath(Object path) ;
	public List findByPort(Object port) ;
	public List findAll() ;
	public Uri merge(Uri detachedInstance) ;
	public void attachDirty(Uri instance) ;
	public void attachClean(Uri instance) ;
}