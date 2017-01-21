package by.pvt.pintusov.courses.dao.Impl;

import by.pvt.pintusov.courses.dao.AbstractDao;
import by.pvt.pintusov.courses.pojos.TestClass;
import by.pvt.pintusov.courses.pojos.User;
import by.pvt.pintusov.courses.utils.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;

/**
 * Project name: courses
 * Created by Дмитрий
 * Date: 20.01.2017.
 */
public class TestClassDaoImpl extends AbstractDao<TestClass> {
	private static Logger logger = Logger.getLogger(UserDaoImpl.class);
	private HibernateUtil util = HibernateUtil.getInstance();
	private static TestClassDaoImpl instance;

	public static synchronized TestClassDaoImpl getInstance(){
		if(instance == null){
			instance = new  TestClassDaoImpl();
		}
		return instance;
	}

	private TestClassDaoImpl(){
		super(User.class);
	}

}
