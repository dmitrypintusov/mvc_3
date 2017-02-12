package by.pvt.pintusov.courses.services.impl;

import by.pvt.pintusov.courses.dao.ICourseDao;
import by.pvt.pintusov.courses.dao.Impl.CourseDaoImpl;
import by.pvt.pintusov.courses.enums.CourseStatusType;
import by.pvt.pintusov.courses.enums.ServiceConstants;
import by.pvt.pintusov.courses.exceptions.DaoException;
import by.pvt.pintusov.courses.exceptions.ServiceException;
import by.pvt.pintusov.courses.pojos.Archive;
import by.pvt.pintusov.courses.pojos.Course;
import by.pvt.pintusov.courses.pojos.User;
import by.pvt.pintusov.courses.services.AbstractService;
import by.pvt.pintusov.courses.services.ICourseService;
import by.pvt.pintusov.courses.utils.TransactionUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;
import java.util.Set;

/**
 * Project: courses
 * Created by: USER
 * Date: 24.01.17.
 */
public class CourseServiceImpl extends AbstractService<Course> implements ICourseService {
	private static Logger logger = Logger.getLogger(CourseServiceImpl.class);

	@Autowired
	private ICourseDao courseDao;

	@Autowired
	public CourseServiceImpl (ICourseDao courseDao) {
		super (courseDao);
		this.courseDao = courseDao;
	}

	@Override
	@Transactional (propagation = Propagation.SUPPORTS, readOnly = true)
	public int getNumberOfPages(int recordsPerPage) throws ServiceException{
		int numberOfPages;
		try {
			Long numberOfRecords = courseDao.getNumberRecords();
			numberOfPages = (int) Math.ceil(numberOfRecords * 1.0 / recordsPerPage);
			logger.info(ServiceConstants.TRANSACTION_SUCCEEDED);
		}
		catch (DaoException e) {
			logger.error(ServiceConstants.TRANSACTION_FAILED, e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			throw new ServiceException(ServiceConstants.TRANSACTION_FAILED + e);
		}
		return numberOfPages;
	}

	@Override
	@Transactional (propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Course> getAllToPage(int recordsPerPage, int pageNumber, String sorting) throws ServiceException {
		List<Course> results;
		try {
			results = courseDao.getCourses(recordsPerPage, pageNumber, sorting);
			logger.info(ServiceConstants.TRANSACTION_SUCCEEDED);
		}
		catch (DaoException e) {
			logger.error(ServiceConstants.TRANSACTION_FAILED, e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			throw new ServiceException(ServiceConstants.TRANSACTION_FAILED + e);
		}
		return results;
	}
}
