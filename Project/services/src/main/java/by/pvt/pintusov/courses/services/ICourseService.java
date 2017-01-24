package by.pvt.pintusov.courses.services;

import by.pvt.pintusov.courses.enums.CourseStatusType;
import by.pvt.pintusov.courses.exceptions.ServiceException;
import by.pvt.pintusov.courses.pojos.Archive;
import by.pvt.pintusov.courses.pojos.Course;
import by.pvt.pintusov.courses.pojos.User;

import java.util.Set;

/**
 * Project: courses
 * Created by: USER
 * Date: 24.01.17.
 */
public interface ICourseService extends IService<Course> {

	Set<User> getCourseStudents () throws ServiceException;
	Set<User> getCourseTeachers () throws ServiceException;
	Set<Course> getOpenCourses() throws ServiceException;
	void updateCourseStatus(Integer id, CourseStatusType courseStatus) throws ServiceException;
	boolean checkCourseStatus(Integer id) throws ServiceException;
	void addCourseToArchive(Course course, CourseStatusType courseStatus, Archive archive) throws ServiceException;
}
