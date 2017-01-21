package by.pvt.pintusov.courses.services.impl;

import by.pvt.pintusov.courses.dao.Impl.UserDaoImpl;
import by.pvt.pintusov.courses.enums.AccessLevelType;
import by.pvt.pintusov.courses.exceptions.DaoException;
import by.pvt.pintusov.courses.exceptions.ServiceException;
import by.pvt.pintusov.courses.pojos.Course;
import by.pvt.pintusov.courses.pojos.User;
import by.pvt.pintusov.courses.services.AbstractService;
import by.pvt.pintusov.courses.services.IUserService;
import by.pvt.pintusov.courses.utils.TransactionUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;


/**
 * User service implementation
 * @author pintusov
 * @version 1.1
 */

public class UserServiceImpl extends AbstractService <User> implements IUserService {
	private static Logger logger = Logger.getLogger(UserServiceImpl.class);
	private static UserServiceImpl instance;
	private UserDaoImpl userDao = UserDaoImpl.getInstance();
	private Transaction transaction;


	public static synchronized UserServiceImpl getInstance () {
		if (instance == null) {
			instance = new UserServiceImpl();
		}
		return instance;
	}

	@Override
	public Serializable saveOrUpdate(User user) throws ServiceException {
		Serializable id;
		Session session = util.getSession();
		try {
			transaction = session.beginTransaction();
			id = userDao.saveOrUpdate(user);
			transaction.commit();
			logger.info(TRANSACTION_SUCCEEDED);
			logger.info(user);
		} catch (DaoException e) {
			TransactionUtil.rollback(transaction, e);
			logger.error(TRANSACTION_FAILED, e);
			throw new ServiceException(TRANSACTION_FAILED + e);
		}
		return id;
	}

	@Override
	public User getById(Integer id) throws ServiceException {
		User user;
		Session session = util.getSession();
		try {
			transaction = session.beginTransaction();
			user = userDao.getById(id);
			transaction.commit();
			logger.info(user);
		}
		catch (DaoException e) {
			TransactionUtil.rollback(transaction, e);
			logger.error(TRANSACTION_FAILED, e);
			throw new ServiceException(TRANSACTION_FAILED + e);
		}
		return user;
	}

	@Override
	public User load(Integer id) throws ServiceException {
		User user;
		Session session = util.getSession();
		try {
			transaction = session.beginTransaction();
			user = userDao.load(id);
			transaction.commit();
			logger.info(TRANSACTION_SUCCEEDED);
			logger.info("Load user #" + id);
		}
		catch (DaoException e) {
			TransactionUtil.rollback(transaction, e);
			logger.error(TRANSACTION_FAILED, e);
			throw new ServiceException(TRANSACTION_FAILED + e);
		}
		return user;
	}

	@Override
	public void delete(Integer id) throws ServiceException {
		Session session = util.getSession();
		try {
			transaction = session.beginTransaction();
			userDao.delete(id);
			transaction.commit();
			logger.info(TRANSACTION_SUCCEEDED);
			logger.info("Deleted user #" + id);
		}
		catch (DaoException e) {
			TransactionUtil.rollback(transaction, e);
			logger.error(TRANSACTION_FAILED, e);
			throw new ServiceException(TRANSACTION_FAILED + e);
		}
	}

	@Override
	public boolean checkUserAuthorization(String login, String password) throws ServiceException {
		return false;
	}

	@Override
	public User getUserByLogin(String login) throws ServiceException {
		User user;
		Session session = util.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			user = userDao.getUserByLogin(login);
			transaction.commit();
		}
		catch (DaoException e) {
			TransactionUtil.rollback(transaction, e);
			logger.error(TRANSACTION_FAILED, e);
			throw new ServiceException(TRANSACTION_FAILED + e);
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
