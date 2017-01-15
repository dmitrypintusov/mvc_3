package by.pvt.pintusov.courses.dao;

import by.pvt.pintusov.courses.pojos.AbstractEntity;
import by.pvt.pintusov.courses.exceptions.DaoException;

import java.io.Serializable;
import java.util.List;

/**
 * Dao interface
 * @author pintusov
 * @version 1.1
 */

public interface IDao <T extends AbstractEntity> {

	void saveOrUpdate(T t) throws DaoException;

	T get(Serializable id) throws DaoException;

	T load(Serializable id) throws DaoException;

	void delete(T t) throws DaoException;
}
