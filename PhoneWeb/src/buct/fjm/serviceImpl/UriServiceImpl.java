package buct.fjm.serviceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import buct.fjm.dao.UriDAO;
import buct.fjm.model.Uri;
import buct.fjm.service.UriService;
@Component("uriService")
@SuppressWarnings("unchecked")
public class UriServiceImpl implements UriService {

	private UriDAO uriDao;
	public Uri findById(int id) {
		return uriDao.findById(id);
	}
	public UriDAO getUriDao() {
		return uriDao;
	}
	@Resource
	public void setUriDao(UriDAO uriDao) {
		this.uriDao = uriDao;
	}

}
