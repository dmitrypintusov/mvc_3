package by.pvt.pintusov.courses.services.impl;

import by.pvt.pintusov.courses.dao.Impl.AccessLevelDaoImpl;
import by.pvt.pintusov.courses.dao.Impl.UserDaoImpl;
import by.pvt.pintusov.courses.enums.AccessLevelType;
import by.pvt.pintusov.courses.enums.ServiceConstants;
import by.pvt.pintusov.courses.exceptions.DaoException;
import by.pvt.pintusov.courses.exceptions.ServiceException;
import by.pvt.pintusov.courses.pojos.AccessLevel;
import by.pvt.pintusov.courses.pojos.User;
import by.pvt.pintusov.courses.services.AbstractService;
import by.pvt.pintusov.courses.services.IUserService;
import by.pvt.pintusov.courses.utils.TransactionUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Set;


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

	//TODO: добавить dao для проверки access level
	@Override
	public AccessLevelType checkAccessLevel(User user) throws ServiceException {
		AccessLevel accessLevel = new AccessLevel();
		Set<AccessLevel> accessLevels = user.getAccessLevels();
		for (AccessLevel access: accessLevels) {
			if(access.equals(AccessLevelType.TEACHER)){
				accessLevel.setAccessLevelType(AccessLevelType.TEACHER);
			} else if (access.equals(AccessLevelType.ADMIN)) {
				accessLevel.setAccessLevelType(AccessLevelType.ADMIN);
			} else {
				accessLevel.setAccessLevelType(AccessLevelType.STUDENT);
			}
		}
		return accessLevel.getAccessLevelType();
	}

	@Override
	public boolean checkIsNewUser(User user) throws ServiceException {
		boolean isNew = false;
		Session session = util.getSession();
		try {
			TransactionUtil.beginTransaction(session);
			if(userDao.getUserByLogin(user.getLogin()) == null){
				isNew = true;
			}
			TransactionUtil.commitTransaction(session);
			logger.info("User " + user + " is new");
		}
		catch (DaoException e) {
			TransactionUtil.rollbackTransaction(session);
			throw new ServiceException(ServiceConstants.TRANSACTION_FAILED + e);
		}
		return isNew;
	}

	@Override
	public void bookUser(User user) throws ServiceException {
		Session session = util.getSession();
		try {
			TransactionUtil.beginTransaction(session);
			AccessLevel accessLevel = new AccessLevel();
			accessLevel.setAccessLevelType(AccessLevelType.STUDENT);
			user.addAccessLevel(accessLevel);
			accessLevel.addUser(user);
			AccessLevelDaoImpl.getInstance().saveOrUpdate(accessLevel);
			userDao.saveOrUpdate(user);
			TransactionUtil.commitTransaction(session);
			logger.info(user);
			logger.info(accessLevel);
		} catch (DaoException e) {
			TransactionUtil.rollbackTransaction(session);
			throw new ServiceException(ServiceConstants.TRANSACTION_FAILED + e);
		}
	}

	public List<User> getAll() throws ServiceException {
		List<User> users;
		Session session = util.getSession();
		try {
			TransactionUtil.beginTransaction(session);
			users = userDao.getAll();
			TransactionUtil.commitTransaction(session);
			logger.info(users);
		}
		catch (DaoException e) {
			TransactionUtil.rollbackTransaction(session);
			throw new ServiceException(ServiceConstants.TRANSACTION_FAILED + e);
		}
		return users;
	}
}
