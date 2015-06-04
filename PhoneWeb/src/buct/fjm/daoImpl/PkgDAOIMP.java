package buct.fjm.daoImpl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;

import buct.fjm.dao.PkgDAO;
import buct.fjm.model.Pkg;

/**
 * A data access object (DAO) providing persistence and search support for Pkg
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see buct.fjm.model.Pkg
 * @author MyEclipse Persistence Tools
 */
@Component("pkgDao")
@SuppressWarnings("unchecked")
public class PkgDAOIMP implements PkgDAO{ 
	private static final Log log = LogFactory.getLog(PkgDAOIMP.class);
	// property constants
	public static final String NATIVE_MAC = "nativeMac";
	public static final String REMOTE_IP = "remoteIp";
	public static final String REMOTE_PORT = "remotePort";
	public static final String PROTOCOL_TYPE = "protocolType";
	public static final String FLOW_DIRECTIOIN = "flowDirectioin";
	public static final String FLOW_AMOUNT = "flowAmount";
	private HibernateTemplate hibernateTemplate;

	protected void initDao() {
		// do nothing
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	public int getCount(String hql)
	{
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		Query   query   =   session.createQuery(hql);
		//Object count=query.list().get(0);
		int test=((Number)query.list().get(0)).intValue();
		session.close();
		return  test; 
	}

	public List<Pkg> queryListObjectAllForPage(String queryString,int pageSize,int page){
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		Query   query   =   session.createQuery(queryString); 
		query.setFirstResult(page); 
		query.setMaxResults(pageSize); 

		List<Pkg> list= (List<Pkg>)query.list();
		session.close();
		return list;
	}
	
	public List<Pkg> getList(String hql){
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		Query   query   =   session.createQuery(hql);
		
		List<Pkg> list=(List<Pkg>)query.list();
		session.close();
		return list;
	}
	
	public void save(Pkg transientInstance) {
		log.debug("saving Pkg instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Pkg persistentInstance) {
		log.debug("deleting Pkg instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Pkg findById(java.lang.Integer id) {
		log.debug("getting Pkg instance with id: " + id);
		try {
			Pkg instance = (Pkg) getHibernateTemplate().get(
					"buct.fjm.model.Pkg", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Pkg instance) {
		log.debug("finding Pkg instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Pkg instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Pkg as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByNativeMac(Object nativeMac) {
		return findByProperty(NATIVE_MAC, nativeMac);
	}

	public List findByRemoteIp(Object remoteIp) {
		return findByProperty(REMOTE_IP, remoteIp);
	}

	public List findByRemotePort(Object remotePort) {
		return findByProperty(REMOTE_PORT, remotePort);
	}

	public List findByProtocolType(Object protocolType) {
		return findByProperty(PROTOCOL_TYPE, protocolType);
	}

	public List findByFlowDirectioin(Object flowDirectioin) {
		return findByProperty(FLOW_DIRECTIOIN, flowDirectioin);
	}

	public List findByFlowAmount(Object flowAmount) {
		return findByProperty(FLOW_AMOUNT, flowAmount);
	}

	public List findAll() {
		log.debug("finding all Pkg instances");
		try {
			String queryString = "from Pkg";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Pkg merge(Pkg detachedInstance) {
		log.debug("merging Pkg instance");
		try {
			Pkg result = (Pkg) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Pkg instance) {
		log.debug("attaching dirty Pkg instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Pkg instance) {
		log.debug("attaching clean Pkg instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static PkgDAO getFromApplicationContext(ApplicationContext ctx) {
		return (PkgDAO) ctx.getBean("PkgDAO");
	}

	public List getSpecialList(String hql) {
		// TODO Auto-generated method stub
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		Query   query   =   session.createQuery(hql);
		
		List list=query.list();
		session.close();
		return list;
	}
}
