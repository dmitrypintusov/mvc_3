package by.pvt.pintusov.courses.dao;

import by.pvt.pintusov.courses.exceptions.DaoException;
import by.pvt.pintusov.courses.pojos.AbstractEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Dao interface
 * @author pintusov
 * @version 1.1
 */

public interface IDao <T extends AbstractEntity> {

	Serializable saveOrUpdate(T entity) throws DaoException;
	T getById(Integer id) throws DaoException;
	T load(Integer id) throws DaoException;
	Long getNumberRecords() throws DaoException;
	List<T> getAll() throws DaoException;
	List<T> getAllToPage(int recordsPerPage, int pageNumber) throws DaoException;
	void delete(Integer id) throws DaoException;
}

