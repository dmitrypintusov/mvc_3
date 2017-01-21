package by.pvt.pintusov.courses.utils;


import by.pvt.pintusov.courses.exceptions.DaoException;
import by.pvt.pintusov.courses.exceptions.ServiceException;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;

/**
 * Project name: courses
 * Created by Дмитрий
 * Date: 20.01.2017.
 */
public class TransactionUtil {
	private static Logger logger = Logger.getLogger(TransactionUtil.class);
	private static final String TRANSACTION_ROLLBACK_FAILED = "Error while rollback transaction: ";

	private TransactionUtil() {
	}

	public static void rollback(Transaction transaction, DaoException e) throws ServiceException {
		if (transaction != null) {
			try {
				transaction.rollback();
			} catch (HibernateException he) {
				logger.error(he);
				throw new ServiceException(TRANSACTION_ROLLBACK_FAILED + e);
			}
		}
	}
}
