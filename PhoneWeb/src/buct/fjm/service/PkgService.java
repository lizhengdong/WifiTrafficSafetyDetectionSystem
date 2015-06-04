package buct.fjm.service;

import java.util.List;

import buct.fjm.model.Pkg;

@SuppressWarnings("unchecked")
public interface PkgService {
	public int getCount(String hql);
	public List<Pkg> getListBySql(String hql,int page,int pageSize);
	public List<Pkg> getList(String hql);
	public void save(Pkg transientInstance) ;
	public void delete(Pkg persistentInstance) ;
	public Pkg findById(java.lang.Integer id) ;
	public List<Pkg> findByExample(Pkg instance) ;
	public List findByProperty(String propertyName, Object value) ;
	public List<Pkg> findAll() ;
	public Pkg merge(Pkg detachedInstance) ;
	public void attachDirty(Pkg instance) ;
	public void attachClean(Pkg instance) ;
}
