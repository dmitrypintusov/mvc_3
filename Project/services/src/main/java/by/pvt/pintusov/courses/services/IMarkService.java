package by.pvt.pintusov.courses.services;

import by.pvt.pintusov.courses.exceptions.ServiceException;
import by.pvt.pintusov.courses.pojos.Mark;

/**
 * Project: courses
 * Created by: USER
 * Date: 24.01.17.
 */
public interface IMarkService extends IService<Mark> {

	void addMark (Integer userId, Integer mark, String courseName) throws ServiceException;
}
