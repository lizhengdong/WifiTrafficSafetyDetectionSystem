package buct.fjm.daoImpl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import buct.fjm.dao.ClientlistDAO;
import buct.fjm.model.Clientlist;

@Component("clientListDao")
@SuppressWarnings("unchecked")
public class ClientDAOIMP implements ClientlistDAO{
	private static final Log log = LogFactory.getLog(ClientDAOIMP.class);
	public static final String CMAC = "cmac";
	public static final String SHOWPKGS = "showpkgs";
	private HibernateTemplate hibernateTemplate;
	
	protected void initDao(){
		//do noting
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	public List findAll(){
		log.debug("finding all Clientlist instances");
		try {
			String queryString = "from Clientlist";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed",re);
			throw re;
		}
	}

	public void attachDirty(Clientlist instance){
		log.debug("attaching dirty Clientlist instances");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed",re);
			throw re;
		}
	}
	
	public void add(Clientlist transientInstance){
		log.debug("adding Clientlist instances");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("add successful");
		} catch (RuntimeException re) {
			log.error("add failed",re);
			throw re;
		}
	}
	
	public void delete(Clientlist persistentInstance){
		log.debug("deleting Clientlist instances");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed",re);
			throw re;
		}
	}
	
	public List queryMac(String queryString){
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		Query query = session.createQuery(queryString);
		
		List list = (List)query.list();
		session.close();
		return list;
	}
}
