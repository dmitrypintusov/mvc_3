package by.pvt.pintusov.courses.dao;

import by.pvt.pintusov.courses.exceptions.DaoException;
import by.pvt.pintusov.courses.pojos.Course;

/**
 * Project name: courses
 * Created by Дмитрий
 * Date: 22.01.2017.
 */
public interface ICourseDao extends IDao<Course> {
	boolean isCourseStatusEnded (Integer id) throws DaoException;

}
