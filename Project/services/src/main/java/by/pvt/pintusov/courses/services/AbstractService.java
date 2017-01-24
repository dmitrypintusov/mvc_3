package by.pvt.pintusov.courses.services;

import by.pvt.pintusov.courses.dao.AbstractDao;
import by.pvt.pintusov.courses.enums.ServiceConstants;
import by.pvt.pintusov.courses.exceptions.DaoException;
import by.pvt.pintusov.courses.exceptions.ServiceException;
import by.pvt.pintusov.courses.pojos.AbstractEntity;
import by.pvt.pintusov.courses.utils.HibernateUtil;
import by.pvt.pintusov.courses.utils.TransactionUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;

/**
 * Service abstract class implements IService
 * @author pintusov
 * @version 1.1
 */

abstract public class AbstractService <T extends AbstractEntity> implements IService <T> {
	private static Logger logger = Logger.getLogger(AbstractService.class);
	protected HibernateUtil util = HibernateUtil.getInstance();
	protected Transaction transaction;
	private AbstractDao abstractDao;
	private Class persistentClass;

	protected AbstractService (Class persistentClass, AbstractDao abstractDao) {
		this.persistentClass = persistentClass;
		this.abstractDao = abstractDao;
	}

	@Override
	public Serializable saveOrUpdate(T entity) throws ServiceException {
		Serializable id;
		Session session = util.getSession();
		try {
			transaction = session.beginTransaction();
			id = abstractDao.saveOrUpdate(entity);
			transaction.commit();
			logger.info(ServiceConstants.TRANSACTION_SUCCEEDED);
			logger.info(entity);
		} catch (DaoException e) {
			TransactionUtil.rollback(transaction, e);
			logger.error(ServiceConstants.TRANSACTION_FAILED, e);
			throw new ServiceException(ServiceConstants.TRANSACTION_FAILED + e);
		}
		return id;
	}

	@Override
	public T getById(Integer id) throws ServiceException {
		T entity;
		Session session = util.getSession();
		try {
			transaction = session.beginTransaction();
			entity = (T) abstractDao.getById(id);
			transaction.commit();
			logger.info(ServiceConstants.TRANSACTION_SUCCEEDED);
			logger.info(entity);
		}
		catch (DaoException e) {
			TransactionUtil.rollback(transaction, e);
			logger.error(ServiceConstants.TRANSACTION_FAILED, e);
			throw new ServiceException(ServiceConstants.TRANSACTION_FAILED + e);
		}
		return entity;
	}

	@Override
	public T load(Integer id) throws ServiceException {
		T entity;
		Session session = util.getSession();
		try {
			transaction = session.beginTransaction();
			entity = (T) abstractDao.load(id);
			transaction.commit();
			logger.info(ServiceConstants.TRANSACTION_SUCCEEDED);
			logger.info(entity);
		}
		catch (DaoException e) {
			TransactionUtil.rollback(transaction, e);
			logger.error(ServiceConstants.TRANSACTION_FAILED, e);
			throw new ServiceException(ServiceConstants.TRANSACTION_FAILED + e);
		}
		return entity;
	}

	@Override
	public void delete(Integer id) throws ServiceException {
		Session session = util.getSession();
		try {
			transaction = session.beginTransaction();
			abstractDao.delete(id);
			transaction.commit();
			logger.info(ServiceConstants.TRANSACTION_SUCCEEDED);
		}
		catch (DaoException e) {
			TransactionUtil.rollback(transaction, e);
			logger.error(ServiceConstants.TRANSACTION_FAILED, e);
			throw new ServiceException(ServiceConstants.TRANSACTION_FAILED + e);
		}
	}
}

