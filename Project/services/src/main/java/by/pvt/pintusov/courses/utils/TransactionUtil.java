package by.pvt.pintusov.courses.utils;


import by.pvt.pintusov.courses.enums.ServiceConstants;
import by.pvt.pintusov.courses.exceptions.ServiceException;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Project name: courses
 * Created by Дмитрий
 * Date: 20.01.2017.
 */
public class TransactionUtil {
	private static Logger logger = Logger.getLogger(TransactionUtil.class);
	private static Transaction transaction;

	private TransactionUtil() {	}

	public static Transaction beginTransaction (Session session) throws ServiceException {
		if (session != null) {
			try {
				transaction = session.beginTransaction();
			} catch (HibernateException he) {
				throw new ServiceException(ServiceConstants.TRANSACTION_BEGIN_FAILED + he);
			}
		}
		return transaction;
	}

	public static void commitTransaction (Session session) throws ServiceException {
		if (session != null || transaction != null) {
			try {
				session.getTransaction().commit();
				logger.info(ServiceConstants.TRANSACTION_SUCCEEDED);
			} catch (HibernateException he) {
				throw new ServiceException(ServiceConstants.TRANSACTION_COMMIT_FAILED + he);
			}
		}
	}

	public static void rollbackTransaction (Session session) throws ServiceException {
		if (session != null || transaction != null) {
			try {
				session.getTransaction().rollback();
			} catch (HibernateException he) {
				logger.error(he);
				throw new ServiceException(ServiceConstants.TRANSACTION_ROLLBACK_FAILED + he);
			}
		}
	}
}
