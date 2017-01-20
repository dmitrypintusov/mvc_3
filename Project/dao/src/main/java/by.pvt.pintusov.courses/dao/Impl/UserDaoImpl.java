package by.pvt.pintusov.courses.dao.Impl;

import by.pvt.pintusov.courses.constants.DaoConstants;
import by.pvt.pintusov.courses.dao.AbstractDao;
import by.pvt.pintusov.courses.dao.IUserDao;
import by.pvt.pintusov.courses.exceptions.DaoException;
import by.pvt.pintusov.courses.pojos.User;
import by.pvt.pintusov.courses.utils.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * User Dao implementation
 * @author pintusov
 * @version 1.1
 */

public class UserDaoImpl extends AbstractDao <User> implements IUserDao {
	private static Logger logger = Logger.getLogger(UserDaoImpl.class);
	private HibernateUtil util = HibernateUtil.getInstance();
	private static UserDaoImpl instance;

	public static synchronized UserDaoImpl getInstance(){
		if(instance == null){
			instance = new UserDaoImpl();
		}
		return instance;
	}

	private UserDaoImpl(){
		super(User.class);
	}
	private UserDaoImpl(SessionFactory sessionFactory){
		super(User.class, sessionFactory);
	}

	@Override
	public User getUserByLogin(String login) throws DaoException {
		User user;
		try {
			Session session = util.getSession();
			Query query = session.createQuery(DaoConstants.HQL_GET_BY_LOGIN);
			query.setParameter(DaoConstants.PARAMETER_USER_LOGIN, login);
			user = (User) query.uniqueResult();
		} catch(HibernateException e){
			logger.error(DaoConstants.ERROR_USER_BY_LOGIN + e);
			throw new DaoException(DaoConstants.ERROR_USER_BY_LOGIN, e);
		}
		return user;
	}
}
