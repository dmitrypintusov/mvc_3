package by.pvt.pintusov.courses.dao.Impl;

import by.pvt.pintusov.courses.dao.AbstractDao;
import by.pvt.pintusov.courses.dao.IMarkDao;
import by.pvt.pintusov.courses.pojos.Mark;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Project: courses
 * Created by: USER
 * Date: 23.01.17.
 */
public class MarkDaoImpl extends AbstractDao<Mark> implements IMarkDao {
	private static Logger logger = Logger.getLogger(MarkDaoImpl.class);

	@Autowired
	private MarkDaoImpl(SessionFactory sessionFactory){
		super(Mark.class, sessionFactory);
	}
}
