package by.pvt.pintusov.courses.inheritanceDAO;

import by.pvt.pintusov.courses.enheritance.Person;
import by.pvt.pintusov.courses.exceptions.DaoException;

import java.io.Serializable;

/**
 * Project name: courses
 * Created by Дмитрий
 * Date: 23.01.2017.
 */
public interface IInheritanceDao <T extends Person> {

	Integer saveOrUpdate(T entity) throws DaoException;
	T getById(Integer id) throws DaoException;
}
