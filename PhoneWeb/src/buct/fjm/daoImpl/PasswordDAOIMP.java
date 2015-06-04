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

import buct.fjm.dao.PasswordDAO;
import buct.fjm.model.Lpassword;

@Component("passwordDao")
@SuppressWarnings("unchecked")
public class PasswordDAOIMP implements PasswordDAO{
	private static final Log log = LogFactory.getLog(PasswordDAOIMP.class);
	//// property constants
	public static final String LOGIN_PW = "loginPw";
	private HibernateTemplate hibernateTemplate;
	protected void initDao(){
		//do nothing
	}
	public HibernateTemplate getHibernateTemplate(){
		return hibernateTemplate;
	}
	@Resource
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate){
		this.hibernateTemplate = hibernateTemplate;
	}
	
	public List findAll(){
		log.debug("finding all Password instances");
		try{
			String queryString = "from Password";
			return getHibernateTemplate().find(queryString);
		}
		catch(RuntimeException re){
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public void attachDirty(Lpassword instance){
		log.debug("attaching dirty Password instances");
		try{
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		}
		catch(RuntimeException re){
			log.error("attach failed", re);
			throw re;
		}
	}
	
	public Lpassword findById(java.lang.Integer id){
		log.debug("getting Lpassword instance with id: "+id);
		try{
			Lpassword instance = (Lpassword)getHibernateTemplate().get("buct.fjm.model.Lpassword", id);
			return instance;
		}
		catch(RuntimeException re){
			log.error("get failed", re);
			throw re;
		}
	}

}
