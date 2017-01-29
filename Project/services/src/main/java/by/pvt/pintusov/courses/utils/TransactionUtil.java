package by.pvt.pintusov.courses.utils;


import by.pvt.pintusov.courses.enums.ServiceConstants;
import by.pvt.pintusov.courses.exceptions.DaoException;
import by.pvt.pintusov.courses.exceptions.ServiceException;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * Project name: courses
 * Created by Дмитрий
 * Date: 20.01.2017.
 */
public class TransactionUtil {
	private static Logger logger = Logger.getLogger(TransactionUtil.class);
	private static HibernateUtil util;
	private static Session session;
	private static SessionFactory sessionFactory;
	private static Transaction transaction;

	private TransactionUtil() {	}

	public static void rollback(Transaction transaction, DaoException e) throws ServiceException {
		if(transaction != null){
			try {
				transaction.rollback();
			}
			catch(HibernateException he){
				logger.error(he);
				throw new ServiceException(ServiceConstants.TRANSACTION_ROLLBACK_FAILED + e);
			}
		}
	}

	//TODO: встроить методы по транзакциям
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

	public static void commitTransaction () throws ServiceException {
		if (sessionFactory != null || transaction != null) {
			try {
				session.getTransaction().commit();
				logger.info(ServiceConstants.TRANSACTION_SUCCEEDED);
			} catch (HibernateException he) {
				throw new ServiceException(ServiceConstants.TRANSACTION_COMMIT_FAILED + he);
			}
		}
	}

	public static void rollbackTransaction () throws ServiceException {
		if (sessionFactory != null || transaction != null) {
			try {
				session.getTransaction().rollback();
			} catch (HibernateException he) {
				logger.error(he);
				throw new ServiceException(ServiceConstants.TRANSACTION_ROLLBACK_FAILED + he);
			}
		}
	}
}
