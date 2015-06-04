package buct.fjm.dao;
import java.util.List;

import buct.fjm.model.Tpkg;
@SuppressWarnings("unchecked")
public interface TpkgDAO  {
	public int getCount(String hql);
	public List<Tpkg> queryListObjectAllForPage(String queryString,int pageSize,int page);
	public List<Tpkg> queryListObjectAll(String queryString);
	public List queryMac(String queryString);
	public void save(Tpkg transientInstance) ;
	public void delete(Tpkg persistentInstance) ;
	public Tpkg findById(java.lang.Integer id) ;
	public List findByExample(Tpkg instance) ;
	public List findByProperty(String propertyName, Object value) ;
	public List findByTnativeMac(Object tnativeMac) ;
	public List findByTremoteIp(Object tremoteIp) ;
	public List findByTremotePort(Object tremotePort) ;
	public List findByTprotocolType(Object tprotocolType) ;
	public List findByTflowDirection(Object tflowDirection) ;
	public List findByTflowAmount(Object tflowAmount) ;
	public List findAll() ;
	public Tpkg merge(Tpkg detachedInstance) ;
	public void attachDirty(Tpkg instance) ;
	public void attachClean(Tpkg instance) ;
}