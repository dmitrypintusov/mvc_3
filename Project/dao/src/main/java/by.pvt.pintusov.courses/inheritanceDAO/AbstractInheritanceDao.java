package by.pvt.pintusov.courses.inheritanceDAO;

import by.pvt.pintusov.courses.constants.DaoConstants;
import by.pvt.pintusov.courses.enheritance.Person;
import by.pvt.pintusov.courses.exceptions.DaoException;
import by.pvt.pintusov.courses.utils.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.io.Serializable;

/**
 * Project name: courses
 * Created by Дмитрий
 * Date: 23.01.2017.
 */
public class AbstractInheritanceDao <T extends Person> implements IInheritanceDao<T> {
	private static Logger logger = Logger.getLogger (AbstractInheritanceDao.class);
	protected HibernateUtil util = HibernateUtil.getInstance();
	private Class persistentClass;

	protected AbstractInheritanceDao (Class persistentClass) { this.persistentClass = persistentClass; }

	@Override
	public Integer saveOrUpdate(T entity) throws DaoException {
		Integer id;
		try {
			Session session = util.getSession();
			session.saveOrUpdate(entity);
			logger.info("saved " + entity);
			id = (Integer) session.getIdentifier(entity);
			logger.info("entity " + entity + " id=" + id);
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
}
