package by.pvt.pintusov.courses.services.impl;

import by.pvt.pintusov.courses.dao.Impl.CourseDaoImpl;
import by.pvt.pintusov.courses.enums.CourseStatusType;
import by.pvt.pintusov.courses.exceptions.ServiceException;
import by.pvt.pintusov.courses.pojos.Archive;
import by.pvt.pintusov.courses.pojos.Course;
import by.pvt.pintusov.courses.pojos.User;
import by.pvt.pintusov.courses.services.AbstractService;
import by.pvt.pintusov.courses.services.ICourseService;
import org.apache.log4j.Logger;

import java.util.Set;

/**
 * Project: courses
 * Created by: USER
 * Date: 24.01.17.
 */
public class CourseServiceImpl extends AbstractService<Course> implements ICourseService {
	private static Logger logger = Logger.getLogger(CourseServiceImpl.class);
	private static CourseServiceImpl instance;
	private CourseDaoImpl courseDao = CourseDaoImpl.getInstance();

	public static synchronized CourseServiceImpl getInstance () {
		if (instance == null) {
			instance = new CourseServiceImpl();
		}
		return instance;
	}

	private CourseServiceImpl () {
		super (Course.class, CourseDaoImpl.getInstance());
	}

	@Override
	public Set<User> getCourseStudents() throws ServiceException {
		return null;
	}

	@Override
	public Set<User> getCourseTeachers() throws ServiceException {
		return null;
	}

	@Override
	public Set<Course> getOpenCourses() throws ServiceException {
		return null;
	}

	@Override
	public void updateCourseStatus(Integer id, CourseStatusType courseStatus) throws ServiceException {

	}

	@Override
	public boolean checkCourseStatus(Integer id) throws ServiceException {
		return false;
	}

	@Override
	public void addCourseToArchive(Course course, CourseStatusType courseStatus, Archive archive) throws ServiceException {

	}
}
