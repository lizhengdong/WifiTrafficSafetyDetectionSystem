package buct.fjm.daoImpl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;

import buct.fjm.dao.AccesscontrolDAO;
import buct.fjm.model.Accesscontrol;

/**
 * A data access object (DAO) providing persistence and search support for
 * Accesscontrol entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see buct.fjm.hibernate.Accesscontrol
 * @author MyEclipse Persistence Tools
 */
@Component("accessControlDao")
@SuppressWarnings("unchecked")
public class AccesscontrolDAOIMP implements AccesscontrolDAO{ 
	private static final Log log = LogFactory.getLog(AccesscontrolDAOIMP.class);
	// property constants
	public static final String IP = "ip";
	public static final String PORT = "port";
	public static final String ACCESS_TYPE = "accessType";
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

	public void save(Accesscontrol transientInstance) {
		log.debug("saving Accesscontrol instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Accesscontrol persistentInstance) {
		log.debug("deleting Accesscontrol instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Accesscontrol findById(java.lang.Integer id) {
		log.debug("getting Accesscontrol instance with id: " + id);
		try {
			Accesscontrol instance = (Accesscontrol) getHibernateTemplate()
					.get("buct.fjm.model.Accesscontrol", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Accesscontrol instance) {
		log.debug("finding Accesscontrol instance by example");
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
		log.debug("finding Accesscontrol instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Accesscontrol as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByIp(Object ip) {
		return findByProperty(IP, ip);
	}

	public List findByPort(Object port) {
		return findByProperty(PORT, port);
	}

	public List findByAccessType(Object accessType) {
		return findByProperty(ACCESS_TYPE, accessType);
	}

	public List findAll() {
		log.debug("finding all Accesscontrol instances");
		try {
			String queryString = "from Accesscontrol";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Accesscontrol merge(Accesscontrol detachedInstance) {
		log.debug("merging Accesscontrol instance");
		try {
			Accesscontrol result = (Accesscontrol) getHibernateTemplate()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Accesscontrol instance) {
		log.debug("attaching dirty Accesscontrol instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Accesscontrol instance) {
		log.debug("attaching clean Accesscontrol instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static AccesscontrolDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (AccesscontrolDAO) ctx.getBean("AccesscontrolDAO");
	}
}
