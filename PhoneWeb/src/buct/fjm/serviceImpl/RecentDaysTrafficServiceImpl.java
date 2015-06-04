package buct.fjm.serviceImpl;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import buct.fjm.dao.PkgDAO;
import buct.fjm.daoImpl.PkgDAOIMP;
import buct.fjm.model.Pkg;
import buct.fjm.service.PkgService;
import buct.fjm.service.RecentDaysTrafficService;
@Component("recentDaysTrafficService")
@SuppressWarnings("unchecked")
public class RecentDaysTrafficServiceImpl implements RecentDaysTrafficService {
	
	
	private PkgDAO pkgDAO;
	
	public PkgDAO getPkgDAO() {
		return pkgDAO;
	}
	@Resource
	public void setPkgDAO(PkgDAO pkgDAO) {
		this.pkgDAO = pkgDAO;
	}

	public Long getDayTraffic(String date) {
		// TODO Auto-generated method stub
		Long DownstreamCount = 0L;
		String hql = "from Pkg where dateVisit like '"+date+"%'";
		try {
			List<Pkg> list = pkgDAO.getList(hql);
			Iterator<Pkg> it = list.iterator();
			while (it.hasNext()) {
				DownstreamCount += Long.parseLong(it.next().getFlowAmount());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return DownstreamCount;
	}
	

	// public List<String> getCountByHql(String hql,String date){
	// List<String> list=null;
	//
	//
	// Session session = hibernateTemplate.getSessionFactory().openSession();
	// Query query = session.createQuery(hql);
	// query.setString(0, date);
	// Transaction ts=session.beginTransaction();
	// try {
	// ts.begin();
	// list=query.list();
	// ts.commit();
	// } catch (Exception e) {
	// // TODO: handle exception
	// e.printStackTrace();
	// if(ts!=null){
	// ts.rollback();
	// }
	// }finally{
	// if(session!=null&&session.isOpen()){
	// session.close();
	// }
	//
	// }
	// return list;
	// }
}
