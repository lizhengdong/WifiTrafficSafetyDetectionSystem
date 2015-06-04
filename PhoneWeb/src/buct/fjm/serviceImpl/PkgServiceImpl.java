package buct.fjm.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import buct.fjm.dao.PkgDAO;
import buct.fjm.model.Pkg;
import buct.fjm.service.PkgService;
@Component("pkgService")
@SuppressWarnings("unchecked")
public class PkgServiceImpl implements PkgService {

	private PkgDAO pkgDAO;
	public void attachClean(Pkg instance) {
		// TODO Auto-generated method stub

	}

	public void attachDirty(Pkg instance) {
		// TODO Auto-generated method stub

	}

	public void delete(Pkg persistentInstance) {
		// TODO Auto-generated method stub

	}

	public List<Pkg> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Pkg> findByExample(Pkg instance) {
		// TODO Auto-generated method stub
		return null;
	}

	public Pkg findById(Integer id) {
		// TODO Auto-generated method stub
		return pkgDAO.findById(id);
	}

	public List findByProperty(String propertyName, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getCount(String hql) {
		// TODO Auto-generated method stub
		return pkgDAO.getCount(hql);
	}

	public List<Pkg> getListBySql(String hql, int page, int pageSize) {
		// TODO Auto-generated method stub
		return pkgDAO.queryListObjectAllForPage(hql, pageSize, page);
	}

	public Pkg merge(Pkg detachedInstance) {
		// TODO Auto-generated method stub
		return null;
	}

	public void save(Pkg transientInstance) {
		// TODO Auto-generated method stub

	}

	public List<Pkg> getList(String hql){
		return pkgDAO.getList(hql);
	}
	
	public PkgDAO getPkgDAO() {
		return pkgDAO;
	}
	@Resource
	public void setPkgDAO(PkgDAO pkgDAO) {
		this.pkgDAO = pkgDAO;
	}
}
