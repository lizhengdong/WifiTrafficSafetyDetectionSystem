package buct.fjm.serviceImpl;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import buct.fjm.dao.PkgDAO;
import buct.fjm.model.Pkg;
import buct.fjm.service.AllTrafficPropotionService;
@Component("AllTrafficPropotionService")
@SuppressWarnings("unchecked")
public class AllTrafficPropotionServiceImpl implements AllTrafficPropotionService{

	private PkgDAO pkgDAO;
	@Resource
	public void setPkgDAO(PkgDAO pkgDAO) {
		this.pkgDAO = pkgDAO;
	}
	public PkgDAO getPkgDAO() {
		return pkgDAO;
	}
	
	public List TrafficPropotionByMacAddress() {
		// TODO Auto-generated method stub
		List list = null;
		String hql = "select nativeMac,sum(flowAmount) from Pkg group by nativeMac";
		try {
			list = pkgDAO.getSpecialList(hql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
