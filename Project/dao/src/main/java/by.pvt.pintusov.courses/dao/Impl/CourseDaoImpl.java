package by.pvt.pintusov.courses.dao.Impl;

import by.pvt.pintusov.courses.constants.DaoConstants;
import by.pvt.pintusov.courses.dao.AbstractDao;
import by.pvt.pintusov.courses.dao.ICourseDao;
import by.pvt.pintusov.courses.enums.CourseStatusType;
import by.pvt.pintusov.courses.exceptions.DaoException;
import by.pvt.pintusov.courses.pojos.Course;
import org.apache.log4j.Logger;
import org.hibernate.*;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.type.StandardBasicTypes;

import java.util.List;

/**
 * Project name: courses
 * Created by Дмитрий
 * Date: 22.01.2017.
 */
public class CourseDaoImpl extends AbstractDao <Course> implements ICourseDao {
	private static Logger logger = Logger.getLogger(CourseDaoImpl.class);
	private static CourseDaoImpl instance;
	private Class persistentClass = Course.class;

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
			Criteria criteria = session.createCriteria(persistentClass);
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

	public Long getNumberRecords() throws DaoException{
		Long numberOfRecords;
		try {
			Session session = util.getSession();
			Criteria criteria = session.createCriteria(persistentClass);
			Projection count = Projections.rowCount();
			criteria.setProjection(count);
			numberOfRecords = (Long) criteria.uniqueResult();
		}
		catch(HibernateException e){
			logger.error(DaoConstants.ERROR_RECORD_NUMBER + e);
			throw new DaoException(DaoConstants.ERROR_RECORD_NUMBER, e);
		}
		return numberOfRecords;
	}

	public List<Course> getAllCourses(int recordsPerPage, int pageNumber, String sorting) throws DaoException {
		List<Course> list;
		try {
			Session session = util.getSession();
			SQLQuery query = session.createSQLQuery(GET_OPERATIONS + sorting);
			query.addScalar("operationDate", StandardBasicTypes.STRING);
			query.addScalar("description", StandardBasicTypes.STRING);
			query.addScalar("amount", StandardBasicTypes.DOUBLE);
			query.addScalar("userLastName", StandardBasicTypes.STRING);
			query.addScalar("accountNumber", StandardBasicTypes.LONG);
			query.setResultTransformer(new ResultTransformer() {
				@Override
				public Object transformTuple(Object[] tuple, String[] aliases) {
					OperationDTO operation = new OperationDTO();
					operation.setOperationDate((String)tuple[0]);
					operation.setDescription((String)tuple[1]);
					operation.setAmount((Double)tuple[2]);
					operation.setUserLastName((String)tuple[3]);
					operation.setAccountNumber((Long)tuple[4]);
					return operation;
				}

				@Override
				public List transformList(List collection) {
					return collection;
				}
			});
			query.setFirstResult((pageNumber - 1) * recordsPerPage);
			query.setMaxResults(recordsPerPage);
			list = query.list();
		} catch (HibernateException e) {
			message = "Unable to get list of operations. Error was thrown in DAO: ";
			logger.error(message + e);
			throw new DaoException(message, e);
		}
		return list;
	}
}
