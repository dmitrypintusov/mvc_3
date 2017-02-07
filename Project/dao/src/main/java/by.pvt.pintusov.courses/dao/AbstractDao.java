package by.pvt.pintusov.courses.dao;

import by.pvt.pintusov.courses.constants.DaoConstants;
import by.pvt.pintusov.courses.exceptions.DaoException;
import by.pvt.pintusov.courses.pojos.AbstractEntity;
import by.pvt.pintusov.courses.utils.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;

/**
 * Describes the abstract class AbstractDao
 * @param <T> - entity of AbstractEntity
 * @author dpintusov
 * @version 1.1
 */

public abstract class AbstractDao <T extends AbstractEntity> implements IDao <T> {
	private static Logger logger = Logger.getLogger(AbstractDao.class);
	protected HibernateUtil util = HibernateUtil.getInstance();
	private Class persistentClass;
	private SessionFactory sessionFactory;

	protected AbstractDao (Class persistentClass) {
		this.persistentClass = persistentClass;
	}

	protected AbstractDao(Class persistentClass, SessionFactory sessionFactory){
		this.persistentClass = persistentClass;
		this.sessionFactory = sessionFactory;
	}
	protected Session getCurrentSession(){
		return sessionFactory.getCurrentSession();
	}

	@Override
	public Serializable saveOrUpdate(T entity) throws DaoException {
		Serializable id;
		try {
			Session session = util.getSession();
			session.saveOrUpdate(entity);
			id = session.getIdentifier(entity);
		}
		catch (HibernateException e) {
			logger.error(DaoConstants.ERROR_DAO + e);
			throw new DaoException();
		}
		return id;
	}

	@Override
	public T getById(Integer id) throws DaoException {
		T entity;
		try {
			Session session = util.getSession();
			entity = (T) session.get(persistentClass, id);
		} catch (HibernateException e) {
			logger.error(DaoConstants.ERROR_DAO + e);
			throw new DaoException();
		}
		return entity;
	}

	@Override
	public T load(Integer id) throws DaoException {
		T entity;
		try {
			Session session = util.getSession();
			entity = (T) session.load(persistentClass, id, LockMode.READ);
		} catch (HibernateException e) {
			logger.error(DaoConstants.ERROR_DAO + e);
			throw new DaoException();
		}
		return entity;
	}

	@Override
	public void delete(Integer id) throws DaoException {
		try {
			Session session = util.getSession();
			T entity = (T) session.get(persistentClass, id);
			session.delete(entity);
		}catch (HibernateException e) {
			logger.error(DaoConstants.ERROR_DAO + e);
			throw new DaoException();
		}
	}
}
