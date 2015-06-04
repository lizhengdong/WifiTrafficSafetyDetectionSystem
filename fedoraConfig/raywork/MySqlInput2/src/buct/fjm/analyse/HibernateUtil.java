package buct.fjm.analyse;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtil {
	private SessionFactory sessionFactory;//会话工厂，用于创建会话
	private Session session;//hibernate会话
	private Transaction transaction; //hiberante事务
	private static HibernateUtil hibernateUtil;
	
	
	protected Session getSession() {
		return session;
	}

	public HibernateUtil(){
		sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
	}
	
	/*public static HibernateUtil getInstance(){
		if(hibernateUtil==null){
			hibernateUtil=new HibernateUtil();
		}
		return hibernateUtil;
	}*/
	
	/**
	 * 根据hql查询数据库
	 * @author fjm
	 */
	public List Search(String hql){
		/*SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
		Session session = sessionFactory.getCurrentSession();*/
		//Session session =HibernateUtil.getSession();
		beginTransaction();
		Query   query   =   session.createQuery(hql); 
		//query.setCacheable(true);
		List list= query.list();
		endTransaction(false);
		//session.close();
		return list;
	}
	
	/**
	*开始一个hibernate事务
	*/
	protected void beginTransaction()
	throws HibernateException {

	session = sessionFactory.openSession();
	transaction = session.beginTransaction();
	}

	/**
	*结束一个hibernate事务。
	*/
	protected void endTransaction(boolean commit)
	throws HibernateException {

		if (commit) {
		transaction.commit();
		} else {
		//如果是只读的操作，不需要commit这个事务。
		transaction.rollback();
		}
		session.close();
	}
	/**
	 * 释放链接
	 * @author fjm
	 */
	protected void releaseConnection(){
		 if(sessionFactory!=null&&(!sessionFactory.isClosed())){
	    	 sessionFactory.close();
	     }
	}
}
