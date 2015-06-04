package buct.fjm.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import buct.fjm.model.Pkg;
import buct.fjm.model.Pkguri;
import buct.fjm.model.Uri;

public class PkgTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Pkg pkg=new Pkg();
		pkg.setNativeMac("34:23:ba:93:a9:a6");
		pkg.setRemoteIp("192.168.29.10");
		pkg.setRemotePort(58813);
		pkg.setProtocolType((short)17);
		pkg.setFlowDirectioin(true);
		pkg.setFlowAmount("44386");
		Date cDate=new Date();
 	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
 	    String timeStr =format.format(cDate);
 	    try {
 	    	cDate=format.parse(timeStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Timestamp time = new Timestamp(cDate.getTime());
		pkg.setDateVisit(time);
		
		Uri uri=new Uri();
		uri.setHost("da.mmarket.com");
		uri.setPath("/mmsdk/mmsdk?func=mmsdk:postactlog&appkey=300001502369&channel=2003001&code=105");
		uri.setPort(58813);
		
		Pkguri pkguri=new Pkguri();
		pkguri.setPkg(pkg);
		pkguri.setUri(uri);
		
		SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		session.save(pkg);
		session.save(uri);
		session.save(pkguri);
		session.getTransaction().commit();
		
		
		
		
		//session.getTransaction().commit();
		
		
		
		//session.save(uri);
		//session.getTransaction().commit();
	}

}
