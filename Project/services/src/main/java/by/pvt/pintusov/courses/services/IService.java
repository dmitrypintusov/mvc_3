package by.pvt.pintusov.courses.services;

import by.pvt.pintusov.courses.pojos.AbstractEntity;
import by.pvt.pintusov.courses.exceptions.ServiceException;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

/**
 * Service interface
 * @author pintusov
 * @version 1.1
 */

public interface IService <T extends AbstractEntity> {

	Serializable saveOrUpdate(T entity) throws ServiceException;
	T getById(Integer id) throws ServiceException;
	List<T> getAll () throws ServiceException;
	void delete(Integer id) throws ServiceException;
}

