package by.pvt.pintusov.courses.services;

import by.pvt.pintusov.courses.dao.IDao;
import by.pvt.pintusov.courses.enums.ServiceConstants;
import by.pvt.pintusov.courses.exceptions.DaoException;
import by.pvt.pintusov.courses.exceptions.ServiceException;
import by.pvt.pintusov.courses.pojos.AbstractEntity;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Service abstract class implements IService
 * @author pintusov
 * @version 1.1
 */

abstract public class AbstractService <T extends AbstractEntity> implements IService <T> {
	private static Logger logger = Logger.getLogger(AbstractService.class);
	private IDao <T> abstractDao;

	@Autowired
	protected TransactionTemplate transactionTemplate;

	protected AbstractService (IDao <T> abstractDao) {
		this.abstractDao = abstractDao;
	}

	@Override
	public Serializable saveOrUpdate(T entity) throws ServiceException {
		return transactionTemplate.execute(new TransactionCallback<Serializable>() {
			@Override
			public Serializable doInTransaction(TransactionStatus status) {
				Serializable id = null;
				try {
					id = abstractDao.saveOrUpdate(entity);
					logger.info(ServiceConstants.TRANSACTION_SUCCEEDED);
					logger.info(entity);
				} catch (DaoException e) {
					logger.info(ServiceConstants.TRANSACTION_FAILED + e);
					status.setRollbackOnly();
				}
				return id;
			}
		});
	}

	@Override
	public T getById(Integer id) throws ServiceException {
		return transactionTemplate.execute(new TransactionCallback<T>() {
			@Override
			public T doInTransaction(TransactionStatus status) {
				T entity = null;
				try {
					entity = (T) abstractDao.getById(id);
					logger.info(ServiceConstants.TRANSACTION_SUCCEEDED);
					logger.info(entity);
				}
				catch (DaoException e) {
					logger.info(ServiceConstants.TRANSACTION_FAILED + e);
					status.setRollbackOnly();
				}
				return entity;
			}
		});
	}

	@Override
	public List<T> getAll() throws ServiceException {
		return transactionTemplate.execute(new TransactionCallback<List<T>>() {
			@Override
			public List<T> doInTransaction(TransactionStatus status) {
				List<T> list = new ArrayList<T>();
				try {
					list = abstractDao.getAll();
					logger.info(ServiceConstants.TRANSACTION_SUCCEEDED);
					logger.info(list);
				} catch (DaoException e) {
					logger.error(ServiceConstants.TRANSACTION_FAILED + e);
					status.setRollbackOnly();
				}
				return list;
			}
		});
	}

	@Override
	public void delete(Integer id) throws ServiceException {
		transactionTemplate.execute(new TransactionCallback<Void>() {
			@Override
			public Void doInTransaction (TransactionStatus status) {
				try {
					abstractDao.delete(id);
					logger.info(ServiceConstants.TRANSACTION_SUCCEEDED);
					logger.info(ServiceConstants.DELETE_MESSAGE + id);
				}
				catch (DaoException e) {
					logger.info(ServiceConstants.TRANSACTION_FAILED + e);
					status.setRollbackOnly();
				}
				return null;
			}
		});

	}
}

