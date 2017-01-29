package by.pvt.pintusov.courses.services.impl;

import by.pvt.pintusov.courses.dao.Impl.AccessLevelDaoImpl;
import by.pvt.pintusov.courses.pojos.AccessLevel;
import by.pvt.pintusov.courses.services.AbstractService;
import by.pvt.pintusov.courses.services.IAccessLevelService;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;

/**
 * Project: courses
 * Created by: USER
 * Date: 24.01.17.
 */
public class AccessLevelServiceImpl extends AbstractService<AccessLevel> implements IAccessLevelService {
	private static Logger logger = Logger.getLogger(AccessLevelServiceImpl.class);
	private static AccessLevelServiceImpl instance;
	private SessionFactory sessionFactory = util.getSessionFactory();
	private AccessLevelDaoImpl accessLevelDao = AccessLevelDaoImpl.getInstance(sessionFactory);

	public static synchronized AccessLevelServiceImpl getInstance (SessionFactory sessionFactory) {
		if (instance == null) {
			instance = new AccessLevelServiceImpl(sessionFactory);
		}
		return instance;
	}

	private AccessLevelServiceImpl (SessionFactory sessionFactory) {
		super (AccessLevel.class, AccessLevelDaoImpl.getInstance(sessionFactory), sessionFactory);
	}
}
