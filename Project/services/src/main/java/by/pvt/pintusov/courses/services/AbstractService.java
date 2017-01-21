package by.pvt.pintusov.courses.services;

import by.pvt.pintusov.courses.pojos.AbstractEntity;
import by.pvt.pintusov.courses.utils.HibernateUtil;

/**
 * Service abstract class implements IService
 * @author pintusov
 * @version 1.1
 */

abstract public class AbstractService <T extends AbstractEntity> implements IService <T> {
	protected HibernateUtil util = HibernateUtil.getInstance();
	protected final String TRANSACTION_SUCCEEDED = "Transaction succeeded";
	protected final String TRANSACTION_FAILED = "Error was thrown in service: ";
}
