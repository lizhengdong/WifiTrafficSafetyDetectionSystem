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

import buct.fjm.dao.TpkguriDAO;
import buct.fjm.model.Tpkguri;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tpkguri entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see buct.fjm.model.Tpkguri
 * @author MyEclipse Persistence Tools
 */
@Component("tPkgUriDao")
@SuppressWarnings("unchecked")
public class TpkguriDAOIMP implements TpkguriDAO{ 
	private static final Log log = LogFactory.getLog(TpkguriDAOIMP.class);
	private HibernateTemplate hibernateTemplate;

	// property constants

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
	public void save(Tpkguri transientInstance) {
		log.debug("saving Tpkguri instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tpkguri persistentInstance) {
		log.debug("deleting Tpkguri instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tpkguri findById(java.lang.Integer id) {
		log.debug("getting Tpkguri instance with id: " + id);
		try {
			Tpkguri instance = (Tpkguri) getHibernateTemplate().get(
					"buct.fjm.model.Tpkguri", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tpkguri instance) {
		log.debug("finding Tpkguri instance by example");
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
		log.debug("finding Tpkguri instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Tpkguri as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all Tpkguri instances");
		try {
			String queryString = "from Tpkguri";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tpkguri merge(Tpkguri detachedInstance) {
		log.debug("merging Tpkguri instance");
		try {
			Tpkguri result = (Tpkguri) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tpkguri instance) {
		log.debug("attaching dirty Tpkguri instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tpkguri instance) {
		log.debug("attaching clean Tpkguri instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TpkguriDAO getFromApplicationContext(ApplicationContext ctx) {
		return (TpkguriDAO) ctx.getBean("TpkguriDAO");
	}
}
