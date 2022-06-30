package in.co.rays.project_3.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate DataSource provides the object of sessionfactory and session. It
 * contains three methods 1) getSessionFactory() 2) getSession() 3)
 * closeSession()
 * 
 * @author Praveen
 *
 */
public final class HibDataSource {
	private static SessionFactory sessionFactory = null;

	public static SessionFactory getSessionFactory() {

		if (sessionFactory == null) {
			sessionFactory = new Configuration().configure().buildSessionFactory();
		}
		return sessionFactory;
	}

	public static Session getSession() {

		Session session = getSessionFactory().openSession();
		return session;

	}

	public static void closeSession(Session session) {
		
		if (session != null) {
			session.close();
		}
	}
}
   