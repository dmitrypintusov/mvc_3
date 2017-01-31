package by.pvt.pintusov.courses.services.impl;

import by.pvt.pintusov.courses.dao.Impl.UserDaoImpl;
import by.pvt.pintusov.courses.enums.AccessLevelType;
import by.pvt.pintusov.courses.enums.ServiceConstants;
import by.pvt.pintusov.courses.exceptions.DaoException;
import by.pvt.pintusov.courses.exceptions.ServiceException;
import by.pvt.pintusov.courses.pojos.Course;
import by.pvt.pintusov.courses.pojos.User;
import by.pvt.pintusov.courses.services.AbstractService;
import by.pvt.pintusov.courses.services.IUserService;
import by.pvt.pintusov.courses.utils.TransactionUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


/**
 * User service implementation
 * @author pintusov
 * @version 1.1
 */

public class UserServiceImpl extends AbstractService <User> implements IUserService {
	private static Logger logger = Logger.getLogger(UserServiceImpl.class);
	private static UserServiceImpl instance;
	private UserDaoImpl userDao = UserDaoImpl.getInstance();

	public static synchronized UserServiceImpl getInstance () {
		if (instance == null) {
			instance = new UserServiceImpl();
		}
		return instance;
	}

	private UserServiceImpl () {
		super (User.class, UserDaoImpl.getInstance());
	}

	@Override
	public boolean checkUserAuthorization(String login, String password) throws ServiceException {
		boolean isAuthorized = false;
		Session session = util.getSession();
		try {
			TransactionUtil.beginTransaction(session);
			isAuthorized = userDao.isAuthorized(login, password);
			TransactionUtil.commitTransaction(session);
			logger.info("User " + login + " is authorized");
		}
		catch (DaoException e) {
			TransactionUtil.rollbackTransaction(session);
			throw new ServiceException(ServiceConstants.TRANSACTION_FAILED + e);
		}
		return isAuthorized;
	}

	@Override
	public User getUserByLogin(String login) throws ServiceException {
		User user;
		Session session = util.getSession();
		try {
			TransactionUtil.beginTransaction(session);
			user = userDao.getUserByLogin(login);
			TransactionUtil.commitTransaction(session);
			logger.info("User by login: " + user);
		}
		catch (DaoException e) {
			TransactionUtil.rollbackTransaction(session);
			throw new ServiceException(ServiceConstants.TRANSACTION_FAILED + e);
		}
		return user;
	}

	@Override
	public AccessLevelType checkAccessLevel(User user) throws ServiceException {
		return null;
	}

	@Override
	public boolean checkIsNewUser(User user) throws ServiceException {
		return false;
	}

	@Override
	public void bookUser(User user, Course course) throws ServiceException {
	}
}
