package by.pvt.pintusov.courses.services.impl;

import by.pvt.pintusov.courses.dao.Impl.AccessLevelDaoImpl;
import by.pvt.pintusov.courses.pojos.AccessLevel;
import by.pvt.pintusov.courses.services.AbstractService;
import by.pvt.pintusov.courses.services.IAccessLevelService;
import org.apache.log4j.Logger;

/**
 * Project: courses
 * Created by: USER
 * Date: 24.01.17.
 */
public class AccessLevelServiceImpl extends AbstractService<AccessLevel> implements IAccessLevelService {
	private static Logger logger = Logger.getLogger(AccessLevelServiceImpl.class);
	private static AccessLevelServiceImpl instance;
	private AccessLevelDaoImpl accessLevelDao = AccessLevelDaoImpl.getInstance();

	public static synchronized AccessLevelServiceImpl getInstance () {
		if (instance == null) {
			instance = new AccessLevelServiceImpl();
		}
		return instance;
	}

	private AccessLevelServiceImpl () {
		super (AccessLevel.class, AccessLevelDaoImpl.getInstance());
	}
}
