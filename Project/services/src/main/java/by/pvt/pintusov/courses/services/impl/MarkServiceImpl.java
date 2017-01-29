package by.pvt.pintusov.courses.services.impl;

import by.pvt.pintusov.courses.dao.Impl.MarkDaoImpl;
import by.pvt.pintusov.courses.pojos.Mark;
import by.pvt.pintusov.courses.services.AbstractService;
import by.pvt.pintusov.courses.services.IMarkService;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;

/**
 * Project: courses
 * Created by: USER
 * Date: 24.01.17.
 */
public class MarkServiceImpl extends AbstractService<Mark> implements IMarkService {
	private static Logger logger = Logger.getLogger(MarkServiceImpl.class);
	private static MarkServiceImpl instance;
	private SessionFactory sessionFactory = util.getSessionFactory();
	private MarkDaoImpl markDao = MarkDaoImpl.getInstance(sessionFactory);

	public static synchronized MarkServiceImpl getInstance (SessionFactory sessionFactory) {
		if (instance == null) {
			instance = new MarkServiceImpl(sessionFactory);
		}
		return instance;
	}

	private MarkServiceImpl (SessionFactory sessionFactory) {
		super (Mark.class, MarkDaoImpl.getInstance(sessionFactory), sessionFactory);
	}
}
