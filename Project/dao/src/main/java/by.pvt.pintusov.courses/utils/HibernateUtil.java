package by.pvt.pintusov.courses.utils;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Project name: courses
 * Created by Дмитрий
 * Date: 15.01.2017.
 */

public class HibernateUtil {
	private static HibernateUtil util = null;
	private static Logger logger = Logger.getLogger(HibernateUtil.class);
	private SessionFactory sessionFactory;
	private static ThreadLocal <Session> sessions = new ThreadLocal <> ();

	public static synchronized HibernateUtil getInstance () {
		if (util == null) {
			util = new HibernateUtil();
		}
		return util;
	}

	private HibernateUtil () {
		try {
			Configuration configuration = new Configuration().configure();
			//TODO: решить проблему
			//configuration.setNamingStrategy(new CustomNamingStrategyUtil());
			StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
			sessionFactory = configuration.buildSessionFactory(serviceRegistryBuilder.build());
		} catch (Throwable e) {
			logger.error("Initial SessionFactory creation failed." + e);
			throw new ExceptionInInitializerError(e);
		}
	}

	public Session getSession () {
		Session session = sessions.get();
		if (session == null) {
			session = sessionFactory.openSession();
			sessions.set(session);
		}
		return session;
	}

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
