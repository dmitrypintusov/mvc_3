package by.pvt.pintusov.courses.dao;

import by.pvt.pintusov.courses.exceptions.DaoException;
import by.pvt.pintusov.courses.pojos.AbstractEntity;
import by.pvt.pintusov.courses.managers.HikariCP;
import by.pvt.pintusov.courses.utils.CoursesSystemLogger;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Abstract Dao class implements Dao interface
 * @author pintusov
 * @version 1.1
 */

public abstract class AbstractDao <T extends AbstractEntity> implements IDao <T> {
	private static Logger logger = Logger.getLogger(AbstractDao.class);
	private Class abstactClass;
	private SessionFactory sessionFactory;

	protected AbstractDao (Class abstractClass, SessionFactory sessionFactory) {
		this.abstactClass = abstractClass;
		this.sessionFactory = sessionFactory;
	}

	protected Session getCurrentSession () {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void saveOrUpdate(T entity) throws DaoException {

	}

	@Override
	public T get(Serializable id) throws DaoException {
		return null;
	}

	@Override
	public T load(Serializable id) throws DaoException {
		return null;
	}

	@Override
	public void delete(T entity) throws DaoException {

	}
}
