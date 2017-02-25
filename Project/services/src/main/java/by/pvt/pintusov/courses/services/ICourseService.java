package by.pvt.pintusov.courses.services;

import by.pvt.pintusov.courses.enums.CourseStatusType;
import by.pvt.pintusov.courses.exceptions.DaoException;
import by.pvt.pintusov.courses.exceptions.ServiceException;
import by.pvt.pintusov.courses.pojos.Archive;
import by.pvt.pintusov.courses.pojos.Course;
import by.pvt.pintusov.courses.pojos.User;

import java.util.List;
import java.util.Set;

/**
 * Project: courses
 * Created by: USER
 * Date: 24.01.17.
 */
public interface ICourseService extends IService<Course> {

	int getNumberOfPages(int recordsPerPage) throws ServiceException;
	List<Course> getAllToPage(int recordsPerPage, int pageNumber, String sorting) throws ServiceException;
	public void updateCourseStatus (String courseName, CourseStatusType courseStatus) throws ServiceException;
	void startCourse (Course course) throws ServiceException;
	Course getByCourseName (String courseName) throws ServiceException;
	boolean isCourseStatusEnded (String courseName) throws ServiceException;
}
