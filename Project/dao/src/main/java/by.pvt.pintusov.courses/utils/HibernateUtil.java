package by.pvt.pintusov.courses.utils;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Project name: courses
 * Created by dpintusov
 * Date: 15.01.2017.
 */

public class HibernateUtil {
	private static HibernateUtil util = null;
	private static Logger logger = Logger.getLogger(HibernateUtil.class);
	private SessionFactory sessionFactory;
	private static ThreadLocal <Session> sessions = new ThreadLocal <> ();

	/**
	 * Singleton of HibernateUtil
	 * @return util - HibernateUtil singleton
	 */
	public static synchronized HibernateUtil getInstance () {
		if (util == null) {
			util = new HibernateUtil();
		}
		return util;
	}

	/**
	 * Constructor of Hibernate util
	 * gets configuration from hibernate.cfg.xml
	 * build sessionFactory
	 */
	private HibernateUtil () {
		try {
			Configuration configuration = new Configuration().configure();
			configuration.setNamingStrategy(new CustomNamingStrategy());
			StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
			sessionFactory = configuration.buildSessionFactory(serviceRegistryBuilder.build());
		} catch (Throwable e) {
			logger.error("Initial SessionFactory creation failed." + e);
			throw new ExceptionInInitializerError(e);
		}
	}

	/**
	 * Gets a session from the  thread local
	 * if session null, open session from the sessionFactory
	 * @return a session from the sessionFactory
	 */
	public Session getSession () {
		Session session = sessions.get();
		if (session == null) {
			session = sessionFactory.openSession();
			sessions.set(session);
		}
		return session;
	}

	/**
	 * Closes current session
	 * @param session - current session
	 */
	public void releaseSession (Session session) {
		if (session != null) {
			try {
				sessions.remove();
			} catch (HibernateException e) {
				logger.error(e);
			}
		}
	}
}
