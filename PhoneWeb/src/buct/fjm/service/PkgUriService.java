package buct.fjm.service;

import java.util.List;

import buct.fjm.model.Pkguri;
@SuppressWarnings("unchecked")
public interface PkgUriService {
	public int getCount(String hql);
	public List<Pkguri> getListBySql(String hql,int page,int pageSize);
	public List getList(String hql);
	//public List<Pkguri> findAll();
}
