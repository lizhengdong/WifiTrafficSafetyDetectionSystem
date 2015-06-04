package buct.fjm.action;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtil {
	private SessionFactory sessionFactory;//�Ự���������ڴ����Ự
	private Session session;//hibernate�Ự
	private Transaction transaction; //hiberante����
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
	 * ���hql��ѯ��ݿ�
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
	*��ʼһ��hibernate����
	*/
	protected void beginTransaction()
	throws HibernateException {

	session = sessionFactory.openSession();
	transaction = session.beginTransaction();
	}

	/**
	*����һ��hibernate����
	*/
	protected void endTransaction(boolean commit)
	throws HibernateException {

		if (commit) {
		transaction.commit();
		} else {
		//�����ֻ���Ĳ���������Ҫcommit�������
		transaction.rollback();
		}
		session.close();
	}
	/**
	 * �ͷ�����
	 * @author fjm
	 */
	protected void releaseConnection(){
		 if(sessionFactory!=null&&(!sessionFactory.isClosed())){
	    	 sessionFactory.close();
	     }
	}
}
