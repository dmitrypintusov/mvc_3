package by.pvt.pintusov.courses.dao.Impl;

import by.pvt.pintusov.courses.constants.DaoConstants;
import by.pvt.pintusov.courses.dao.AbstractDao;
import by.pvt.pintusov.courses.dao.ICourseDao;
import by.pvt.pintusov.courses.enums.CourseStatusType;
import by.pvt.pintusov.courses.exceptions.DaoException;
import by.pvt.pintusov.courses.pojos.Course;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

/**
 * Project name: courses
 * Created by Дмитрий
 * Date: 22.01.2017.
 */
public class CourseDaoImpl extends AbstractDao <Course> implements ICourseDao {
	private static Logger logger = Logger.getLogger(CourseDaoImpl.class);
	private static CourseDaoImpl instance;
	private Class persistenceClass = Course.class;

	private CourseDaoImpl(){
		super(Course.class);
	}

	public static synchronized CourseDaoImpl getInstance(){
		if(instance == null){
			instance = new CourseDaoImpl();
		}
		return instance;
	}

	@Override
	public boolean isCourseStatusEnded(Integer id) throws DaoException {
		boolean isEnded = false;
		try {
			Session session = util.getSession();
			Criteria criteria = session.createCriteria(persistenceClass);
			criteria.add(Restrictions.eq(DaoConstants.PARAMETER_ID, id));
			criteria.add(Restrictions.eq(DaoConstants.PARAMETER_COURSE_STATUS, CourseStatusType.ENDED));
			if (criteria.uniqueResult() != null) {
				isEnded = true;
			}
		} catch(HibernateException e){
			logger.error(DaoConstants.ERROR_COURSE_STATUS + e);
			throw new DaoException(DaoConstants.ERROR_COURSE_STATUS, e);
		}
		return isEnded;
	}
}
