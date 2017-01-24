package by.pvt.pintusov.courses.services.impl;

import by.pvt.pintusov.courses.dao.Impl.MarkDaoImpl;
import by.pvt.pintusov.courses.pojos.Mark;
import by.pvt.pintusov.courses.services.AbstractService;
import by.pvt.pintusov.courses.services.IMarkService;
import org.apache.log4j.Logger;

/**
 * Project: courses
 * Created by: USER
 * Date: 24.01.17.
 */
public class MarkServiceImpl extends AbstractService<Mark> implements IMarkService {
	private static Logger logger = Logger.getLogger(MarkServiceImpl.class);
	private static MarkServiceImpl instance;
	private MarkDaoImpl markDao = MarkDaoImpl.getInstance();

	public static synchronized MarkServiceImpl getInstance () {
		if (instance == null) {
			instance = new MarkServiceImpl();
		}
		return instance;
	}

	private MarkServiceImpl () {
		super (Mark.class, MarkDaoImpl.getInstance());
	}
}
