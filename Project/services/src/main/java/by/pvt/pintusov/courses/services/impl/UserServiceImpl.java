package by.pvt.pintusov.courses.services.impl;

import by.pvt.pintusov.courses.dao.Impl.UserDaoImpl;
import by.pvt.pintusov.courses.pojos.User;
import by.pvt.pintusov.courses.exceptions.DaoException;
import by.pvt.pintusov.courses.exceptions.ServiceException;
import by.pvt.pintusov.courses.services.AbstractService;
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

public class UserServiceImpl extends AbstractService <User> {
	private static Logger logger = Logger.getLogger(UserServiceImpl.class);
	private static UserServiceImpl instance;
	private UserDaoImpl userDao = UserDaoImpl.getInstance();

	private UserServiceImpl () {}

	/**
	 * Singleton for creating UserServiceImpl
	 * @return instance of UserServiceImpl
	 */
	public static synchronized UserServiceImpl getInstance () {
		if (instance == null) {
			instance = new UserServiceImpl();
		}
		return instance;
	}

	@Override
	public Serializable saveOrUpdate(User entity) throws ServiceException {
		Serializable id;
		Session session = util.getSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			id = userDao.saveOrUpdate(entity);
			transaction.commit();
			logger.info(TRANSACTION_SUCCEEDED);
			logger.info(entity);
		} catch (DaoException e) {
			TransactionUtil.rollback(transaction, e);
			logger.error(TRANSACTION_FAILED, e);
			throw new ServiceException(TRANSACTION_FAILED + e);
		}
		return id;
	}

	@Override
	public User getById(Integer id) throws ServiceException {
		return null;
	}

	@Override
	public User load(Integer id) throws ServiceException {
		return null;
	}

	@Override
	public void delete(Integer id) throws ServiceException {

	}
}
