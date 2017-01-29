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
import org.hibernate.SessionFactory;

import java.util.Set;

/**
 * Project: courses
 * Created by: USER
 * Date: 24.01.17.
 */
public class CourseServiceImpl extends AbstractService<Course> implements ICourseService {
	private static Logger logger = Logger.getLogger(CourseServiceImpl.class);
	private static CourseServiceImpl instance;
	private SessionFactory sessionFactory = util.getSessionFactory();
	private CourseDaoImpl courseDao = CourseDaoImpl.getInstance(sessionFactory);

	public static synchronized CourseServiceImpl getInstance (SessionFactory sessionFactory) {
		if (instance == null) {
			instance = new CourseServiceImpl(sessionFactory);
		}
		return instance;
	}

	private CourseServiceImpl (SessionFactory sessionFactory) {
		super (Course.class, CourseDaoImpl.getInstance(sessionFactory), sessionFactory);
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
