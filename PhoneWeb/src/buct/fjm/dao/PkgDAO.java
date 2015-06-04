package buct.fjm.dao;
import java.util.List;

import buct.fjm.model.Pkg;
@SuppressWarnings("unchecked")
public interface PkgDAO  {
	public int getCount(String hql);
	public List<Pkg> queryListObjectAllForPage(String queryString,int pageSize,int page);
	public List<Pkg> getList(String hql);
	public List getSpecialList(String hql);
	public void save(Pkg transientInstance) ;
	public void delete(Pkg persistentInstance) ;
	public Pkg findById(java.lang.Integer id) ;
	public List findByExample(Pkg instance) ;
	public List findByProperty(String propertyName, Object value) ;
	public List findByNativeMac(Object nativeMac) ;
	public List findByRemoteIp(Object remoteIp) ;
	public List findByRemotePort(Object remotePort) ;
	public List findByProtocolType(Object protocolType) ;
	public List findByFlowDirectioin(Object flowDirectioin) ;
	public List findByFlowAmount(Object flowAmount) ;
	public List findAll() ;
	public Pkg merge(Pkg detachedInstance) ;
	public void attachDirty(Pkg instance) ;
	public void attachClean(Pkg instance) ;
}