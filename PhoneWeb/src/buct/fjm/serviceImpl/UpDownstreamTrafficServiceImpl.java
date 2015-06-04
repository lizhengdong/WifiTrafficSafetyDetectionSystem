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
import buct.fjm.model.Pkg;
import buct.fjm.service.UpDownstreamTrafficService;
@Component("UpDownstreamTrafficService")
@SuppressWarnings("unchecked")
public class UpDownstreamTrafficServiceImpl implements UpDownstreamTrafficService{
	
	private HibernateTemplate hibernateTemplate;
	private PkgDAO pkgDAO;
	
	public PkgDAO getPkgDAO() {
		return pkgDAO;
	}
	@Resource
	public void setPkgDAO(PkgDAO pkgDAO) {
		this.pkgDAO = pkgDAO;
	}
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	public Long getAllUpstreamTrafficCount() {
		// TODO Auto-generated method stub
		Long upstreamCount = 0L;
		String hql = "from Pkg where flowDirectioin=0";
		try {
			List<Pkg> list = pkgDAO.getList(hql);
			Iterator<Pkg> it = list.iterator();
			while (it.hasNext()) {
				upstreamCount += Long.parseLong(it.next().getFlowAmount());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return upstreamCount;
	}
	public Long getAllDownstreamTrafficCount() {
		// TODO Auto-generated method stub
		Long downstreamCount = 0L;
		String hql = "from Pkg where flowDirectioin=1";
		try {
			List<Pkg> list = pkgDAO.getList(hql);
			Iterator<Pkg> it = list.iterator();
			while (it.hasNext()) {
				downstreamCount += Long.parseLong(it.next().getFlowAmount());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return downstreamCount;
	}
	public Long getAllstreamTrafficCount() {
		// TODO Auto-generated method stub
		Long AllstreamCount = 0L;
		String hql = "from Pkg";
		try {
			List<Pkg> list = pkgDAO.getList(hql);
			Iterator<Pkg> it = list.iterator();
			while (it.hasNext()) {
				AllstreamCount += Long.parseLong(it.next().getFlowAmount());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return AllstreamCount;
	}

//	public Long getUpstreamTrafficCount(String nativeMac,String remoteIp) {
//		// TODO Auto-generated method stub
//		Long UpStreamCount=0L;
//		String hql="select flowAmount from Pkg where nativeMac=? and remoteIp=? and flowDirectioin=0";
//		List<String> list=getCountByHql(hql,nativeMac,remoteIp);
//		Iterator it=list.iterator();
//		while(it.hasNext()){
//			UpStreamCount+=Long.parseLong(it.next().toString());
//		}
//		return UpStreamCount;
//	}
//	public Long getUpstreamTrafficCount(String nativeMac) {
//		// TODO Auto-generated method stub
//		Long UpStreamCount=0L;
//		String hql="select flowAmount from Pkg where nativeMac=? and flowDirectioin=0";
//		List<String> list=getCountByHql(hql,nativeMac);
//		Iterator it=list.iterator();
//		while(it.hasNext()){
//			UpStreamCount+=Long.parseLong(it.next().toString());
//		}
//		return UpStreamCount;
//	}
//
//	public Long getDownstreamTrafficCount(String nativeMac,String remoteIp) {
//		// TODO Auto-generated method stub
//		Long DownstreamCount=0L;
//		String hql="select flowAmount from Pkg where nativeMac=? and remoteIp=? and flowDirectioin=1";
//		List<String> list=getCountByHql(hql,nativeMac,remoteIp);
//		Iterator it=list.iterator();
//		while(it.hasNext()){
//			DownstreamCount+=Long.parseLong(it.next().toString());
//		}
//		return DownstreamCount;
//	}
//	public Long getDownstreamTrafficCount(String nativeMac) {
//		// TODO Auto-generated method stub
//		Long DownstreamCount=0L;
//		String hql="select flowAmount from Pkg where nativeMac=? and flowDirectioin=1";
//		List<String> list=getCountByHql(hql,nativeMac);
//		Iterator it=list.iterator();
//		while(it.hasNext()){
//			DownstreamCount+=Long.parseLong(it.next().toString());
//		}
//		return DownstreamCount;
//	}
//	public Long getAllstreamTrafficCount(String nativeMac,String remoteIp) {
//		// TODO Auto-generated method stub
//		Long AllstreamCount=0L;
//		String hql="select flowAmount from Pkg where nativeMac=? and remoteIp=?";
//		List<String> list=getCountByHql(hql,nativeMac,remoteIp);
//		Iterator it=list.iterator();
//		while(it.hasNext()){
//			AllstreamCount+=Long.parseLong(it.next().toString());
//		}
//		return AllstreamCount;
//	}
//	public Long getAllstreamTrafficCount(String nativeMac) {
//		// TODO Auto-generated method stub
//		Long AllstreamCount=0L;
//		String hql="select flowAmount from Pkg where nativeMac=?";
//		List<String> list=getCountByHql(hql,nativeMac);
//		Iterator it=list.iterator();
//		while(it.hasNext()){
//			AllstreamCount+=Long.parseLong(it.next().toString());
//		}
//		return AllstreamCount;
//	}
//	public Long getTheAllstreamTrafficCount() {
//		// TODO Auto-generated method stub
//		Long AllstreamCount=0L;
//		String hql="select flowAmount from Pkg";
//		List<String> list=getCountByHql(hql);
//		Iterator it=list.iterator();
//		while(it.hasNext()){
//			AllstreamCount+=Long.parseLong(it.next().toString());
//		}
//		return AllstreamCount;
//	}
	/*
	public List getCountByHql(String hql,String nativeMac,String remoteIp){
		List list=null;
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		Query query = session.createQuery(hql);
		query.setString(0, nativeMac);
		query.setString(1, remoteIp);
		Transaction ts=session.beginTransaction();
		try {
			ts.begin();
			list=query.list();
			ts.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			if(ts!=null){
				ts.rollback();
			}
		}finally{
			if(session!=null&&session.isOpen()){
				session.close();
			}
		}
		return list;
	}

	public List getCountByHql(String hql,String nativeMac){
		List list=null;
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		Query query = session.createQuery(hql);
		query.setString(0, nativeMac);
		Transaction ts=session.beginTransaction();
		try {
			ts.begin();
			list=query.list();
			ts.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			if(ts!=null){
				ts.rollback();
			}
		}finally{
			if(session!=null&&session.isOpen()){
				session.close();
			}
		}
		return list;
	}
	public List getCountByHql(String hql){
		List list=null;
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		Query query = session.createQuery(hql);
		Transaction ts=session.beginTransaction();
		try {
			ts.begin();
			list=query.list();
			ts.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			if(ts!=null){
				ts.rollback();
			}
		}finally{
			if(session!=null&&session.isOpen()){
				session.close();
			}
		}
		return list;
	}
	*/

}
