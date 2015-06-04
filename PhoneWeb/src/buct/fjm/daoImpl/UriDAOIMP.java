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

import buct.fjm.dao.UriDAO;
import buct.fjm.model.Uri;

/**
 * A data access object (DAO) providing persistence and search support for Uri
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see buct.fjm.model.Uri
 * @author MyEclipse Persistence Tools
 */
@Component("uriDao")
@SuppressWarnings("unchecked")
public class UriDAOIMP implements UriDAO{ 
	private static final Log log = LogFactory.getLog(UriDAOIMP.class);
	// property constants
	public static final String HOST = "host";
	public static final String PATH = "path";
	public static final String PORT = "port";
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
	
	public void save(Uri transientInstance) {
		log.debug("saving Uri instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Uri persistentInstance) {
		log.debug("deleting Uri instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Uri findById(java.lang.Integer id) {
		log.debug("getting Uri instance with id: " + id);
		try {
			Uri instance = (Uri) getHibernateTemplate().get(
					"buct.fjm.model.Uri", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Uri instance) {
		log.debug("finding Uri instance by example");
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
		log.debug("finding Uri instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Uri as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByHost(Object host) {
		return findByProperty(HOST, host);
	}

	public List findByPath(Object path) {
		return findByProperty(PATH, path);
	}

	public List findByPort(Object port) {
		return findByProperty(PORT, port);
	}

	public List findAll() {
		log.debug("finding all Uri instances");
		try {
			String queryString = "from Uri";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Uri merge(Uri detachedInstance) {
		log.debug("merging Uri instance");
		try {
			Uri result = (Uri) getHibernateTemplate().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Uri instance) {
		log.debug("attaching dirty Uri instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Uri instance) {
		log.debug("attaching clean Uri instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static UriDAO getFromApplicationContext(ApplicationContext ctx) {
		return (UriDAO) ctx.getBean("UriDAO");
	}
}
