package by.pvt.pintusov.courses.utils;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Project name: courses
 * Created by Дмитрий
 * Date: 15.01.2017.
 */

public class HibernateUtil {
	private static HibernateUtil util = null;
	private static Logger logger = Logger.getLogger(HibernateUtil.class);
	private SessionFactory sessionFactory = null;
	private static ThreadLocal <Session> sessions = new ThreadLocal <> ();

	private HibernateUtil () {
		try {
			Configuration configuration = new Configuration().configure();
			StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
			ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		} catch (Throwable e) {
			logger.error("Initial SessionFactory creation failed." + e);
			System.exit(0);
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

	public static synchronized HibernateUtil getInstance () {
		if (util == null) {
			util = new HibernateUtil();
		}
		return util;
	}
}
