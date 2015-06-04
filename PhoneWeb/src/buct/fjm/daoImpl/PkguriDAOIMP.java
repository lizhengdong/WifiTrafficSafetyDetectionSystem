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
import org.springframework.stereotype.Component;

import buct.fjm.dao.PkguriDAO;
import buct.fjm.model.Pkg;
import buct.fjm.model.Pkguri;

/**
 * A data access object (DAO) providing persistence and search support for
 * Pkguri entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see buct.fjm.model.Pkguri
 * @author MyEclipse Persistence Tools
 */
@Component("pkgUriDao")
@SuppressWarnings("unchecked")
public class PkguriDAOIMP implements PkguriDAO{ 
	private static final Log log = LogFactory.getLog(PkguriDAOIMP.class);
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
	
	public int getCount(String hql)
	{
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		Query   query   =   session.createQuery(hql);
		//Object count=query.list().get(0);
		int test=((Number)query.list().get(0)).intValue();
		session.close();
		return  test; 
	}

	public List<Pkguri> queryListObjectAllForPage(String queryString,int pageSize,int page){
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		Query   query   =   session.createQuery(queryString); 
		query.setFirstResult(pageSize); 
		query.setMaxResults(page); 
		List<Pkguri> list= (List<Pkguri>)query.list();
		session.close();
		return list;
	}
	
	public void save(Pkguri transientInstance) {
		log.debug("saving Pkguri instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Pkguri persistentInstance) {
		log.debug("deleting Pkguri instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Pkguri findById(java.lang.Integer id) {
		log.debug("getting Pkguri instance with id: " + id);
		try {
			Pkguri instance = (Pkguri) getHibernateTemplate().get(
					"buct.fjm.model.Pkguri", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Pkguri instance) {
		log.debug("finding Pkguri instance by example");
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
		log.debug("finding Pkguri instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Pkguri as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all Pkguri instances");
		try {
			String queryString = "from Pkguri";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Pkguri merge(Pkguri detachedInstance) {
		log.debug("merging Pkguri instance");
		try {
			Pkguri result = (Pkguri) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Pkguri instance) {
		log.debug("attaching dirty Pkguri instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Pkguri instance) {
		log.debug("attaching clean Pkguri instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static PkguriDAO getFromApplicationContext(ApplicationContext ctx) {
		return (PkguriDAO) ctx.getBean("PkguriDAO");
	}
	public List getList(String hql) {
		// TODO Auto-generated method stub
		Session session = this.getHibernateTemplate().getSessionFactory().openSession();
		Query   query   =   session.createQuery(hql);
		
		List list=(List)query.list();
		session.close();
		return list;
	}
}
