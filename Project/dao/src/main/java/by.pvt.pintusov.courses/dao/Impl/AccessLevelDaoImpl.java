package by.pvt.pintusov.courses.dao.Impl;

import by.pvt.pintusov.courses.constants.DaoConstants;
import by.pvt.pintusov.courses.dao.AbstractDao;
import by.pvt.pintusov.courses.dao.IAccessLevelDao;
import by.pvt.pintusov.courses.enums.AccessLevelType;
import by.pvt.pintusov.courses.exceptions.DaoException;
import by.pvt.pintusov.courses.pojos.AccessLevel;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Project name: courses
 * Created by Дмитрий
 * Date: 22.01.2017.
 */
public class AccessLevelDaoImpl  extends AbstractDao<AccessLevel> implements IAccessLevelDao {
	private static Logger logger = Logger.getLogger(AccessLevelDaoImpl.class);
	private static AccessLevelDaoImpl instance;

	public static synchronized AccessLevelDaoImpl getInstance(SessionFactory sessionFactory){
		if(instance == null){
			instance = new AccessLevelDaoImpl(sessionFactory);
		}
		return instance;
	}

	private AccessLevelDaoImpl(SessionFactory sessionFactory){
		super(AccessLevel.class, sessionFactory);
	}

	@Override
	public AccessLevel getByAccessLevelType(AccessLevelType accessLevelType) throws DaoException {
		AccessLevel accessLevel;
		try {
			Session session = getCurrentSession();
			Query query = session.createQuery(DaoConstants.HQL_GET_BY_ACCESS_LEVEL);
			query.setParameter(DaoConstants.PARAMETER_ACCESS_LEVEL_TYPE, accessLevelType);
			accessLevel = (AccessLevel) query.uniqueResult();
		} catch(HibernateException e){
			logger.error(DaoConstants.ERROR_ACCESS_LEVEL_TYPE + e);
			throw new DaoException(DaoConstants.ERROR_ACCESS_LEVEL_TYPE, e);
		}
		return accessLevel;
	}
}
