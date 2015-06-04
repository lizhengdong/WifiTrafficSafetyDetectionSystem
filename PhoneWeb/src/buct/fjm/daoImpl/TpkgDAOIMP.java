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

import buct.fjm.dao.TpkgDAO;
import buct.fjm.model.Tpkg;

/**
 * A data access object (DAO) providing persistence and search support for Tpkg
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see buct.fjm.model.Tpkg
 * @author MyEclipse Persistence Tools
 */
@Component("tPkgDao")
@SuppressWarnings("unchecked")
public class TpkgDAOIMP implements TpkgDAO{ 
	private static final Log log = LogFactory.getLog(TpkgDAOIMP.class);
	// property constants
	public static final String TNATIVE_MAC = "tnativeMac";
	public static final String TREMOTE_IP = "tremoteIp";
	public static final String TREMOTE_PORT = "tremotePort";
	public static final String TPROTOCOL_TYPE = "tprotocolType";
	public static final String TFLOW_DIRECTION = "tflowDirection";
	public static final String TFLOW_AMOUNT = "tflowAmount";
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

	public List<Tpkg> queryListObjectAllForPage(String queryString,int pageSize,int page){
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		Query   query   =   session.createQuery(queryString); 
		query.setFirstResult(page); 
		query.setMaxResults(pageSize); 

		List<Tpkg> list= (List<Tpkg>)query.list();
		session.close();
		return list;
	}
	public List<Tpkg> queryListObjectAll(String queryString){
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		Query   query   =   session.createQuery(queryString); 

		List<Tpkg> list= (List<Tpkg>)query.list();
		session.close();
		return list;
	}
	
	public void save(Tpkg transientInstance) {
		log.debug("saving Tpkg instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tpkg persistentInstance) {
		log.debug("deleting Tpkg instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tpkg findById(java.lang.Integer id) {
		log.debug("getting Tpkg instance with id: " + id);
		try {
			Tpkg instance = (Tpkg) getHibernateTemplate().get(
					"buct.fjm.model.Tpkg", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tpkg instance) {
		log.debug("finding Tpkg instance by example");
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
		log.debug("finding Tpkg instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Tpkg as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByTnativeMac(Object tnativeMac) {
		return findByProperty(TNATIVE_MAC, tnativeMac);
	}

	public List findByTremoteIp(Object tremoteIp) {
		return findByProperty(TREMOTE_IP, tremoteIp);
	}

	public List findByTremotePort(Object tremotePort) {
		return findByProperty(TREMOTE_PORT, tremotePort);
	}

	public List findByTprotocolType(Object tprotocolType) {
		return findByProperty(TPROTOCOL_TYPE, tprotocolType);
	}

	public List findByTflowDirection(Object tflowDirection) {
		return findByProperty(TFLOW_DIRECTION, tflowDirection);
	}

	public List findByTflowAmount(Object tflowAmount) {
		return findByProperty(TFLOW_AMOUNT, tflowAmount);
	}

	public List findAll() {
		log.debug("finding all Tpkg instances");
		try {
			String queryString = "from Tpkg";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tpkg merge(Tpkg detachedInstance) {
		log.debug("merging Tpkg instance");
		try {
			Tpkg result = (Tpkg) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tpkg instance) {
		log.debug("attaching dirty Tpkg instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tpkg instance) {
		log.debug("attaching clean Tpkg instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TpkgDAO getFromApplicationContext(ApplicationContext ctx) {
		return (TpkgDAO) ctx.getBean("TpkgDAO");
	}

	public List queryMac(String queryString) {
		// TODO Auto-generated method stub
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		Query query = session.createQuery(queryString);
		
		List list = (List)query.list();
		session.close();
		return list;
	}
}
