package buct.fjm.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import buct.fjm.dao.TpkgDAO;
import buct.fjm.model.Tpkg;
import buct.fjm.service.TPkgService;
@Component("tPkgService")
@SuppressWarnings("unchecked")
public class TPkgServiceImpl implements TPkgService {

	private TpkgDAO tPkgDao;
	public void attachClean(Tpkg instance) {
		// TODO Auto-generated method stub

	}

	public void attachDirty(Tpkg instance) {
		// TODO Auto-generated method stub

	}

	public void delete(Tpkg persistentInstance) {
		// TODO Auto-generated method stub

	}

	public List<Tpkg> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Tpkg> findByExample(Tpkg instance) {
		// TODO Auto-generated method stub
		return null;
	}

	public Tpkg findById(Integer id) {
		// TODO Auto-generated method stub
		return tPkgDao.findById(id);
	}

	public List findByProperty(String propertyName, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getCount(String hql) {
		// TODO Auto-generated method stub
		return tPkgDao.getCount(hql);
	}

	public List<Tpkg> getListBySql(String hql, int page, int pageSize) {
		// TODO Auto-generated method stub
		return tPkgDao.queryListObjectAllForPage(hql, pageSize, page);
	}

	public Tpkg merge(Tpkg detachedInstance) {
		// TODO Auto-generated method stub
		return null;
	}

	public void save(Tpkg transientInstance) {
		// TODO Auto-generated method stub

	}

	public TpkgDAO gettPkgDao() {
		return tPkgDao;
	}
	@Resource
	public void settPkgDao(TpkgDAO tPkgDao) {
		this.tPkgDao = tPkgDao;
	}

	public List queryMac(String hql) {
		// TODO Auto-generated method stub
		return tPkgDao.queryMac(hql);
	}

	public List<Tpkg> getListBySql(String hql) {
		// TODO Auto-generated method stub
		return tPkgDao.queryListObjectAll(hql);
	}

	
}
