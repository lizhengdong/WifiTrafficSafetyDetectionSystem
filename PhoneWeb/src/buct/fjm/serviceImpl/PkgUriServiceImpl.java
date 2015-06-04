package buct.fjm.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import buct.fjm.dao.PkguriDAO;
import buct.fjm.model.Pkguri;
import buct.fjm.service.PkgUriService;
@Component("pkgUriService")
@SuppressWarnings("unchecked")
public class PkgUriServiceImpl implements PkgUriService{

	private PkguriDAO pkgUriDao;
	public int getCount(String hql) {
		// TODO Auto-generated method stub
		return pkgUriDao.getCount(hql);
	}
	
	public List<Pkguri> getListBySql(String hql, int page, int pageSize) {
		// TODO Auto-generated method stub
		return pkgUriDao.queryListObjectAllForPage(hql, page, pageSize);
	}
	

	public PkguriDAO getPkgUriDao() {
		return pkgUriDao;
	}
	@Resource
	public void setPkgUriDao(PkguriDAO pkgUriDao) {
		this.pkgUriDao = pkgUriDao;
	}

	public List getList(String hql) {
		// TODO Auto-generated method stub
		return pkgUriDao.getList(hql);
	}

	/*public PkguriDAO getPkguriDao() {
		return pkguriDao;
	}
	@Resource
	public void setPkguriDao(PkguriDAO pkguriDao) {
		this.pkguriDao = pkguriDao;
	}*/
	
	
	
	
}
