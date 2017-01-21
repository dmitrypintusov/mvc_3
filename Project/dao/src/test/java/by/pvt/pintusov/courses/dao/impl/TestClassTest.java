package by.pvt.pintusov.courses.dao.impl;

import by.pvt.pintusov.courses.dao.Impl.TestClassDaoImpl;
import by.pvt.pintusov.courses.pojos.TestClass;
import by.pvt.pintusov.courses.utils.EntityBuilder;
import by.pvt.pintusov.courses.utils.HibernateUtil;
import org.hibernate.Session;
import org.junit.*;

import java.io.Serializable;

/**
 * Project name: courses
 * Created by Дмитрий
 * Date: 20.01.2017.
 */
public class TestClassTest {
	private TestClass expectedTestClass;
	private TestClass actualTestClass;
	private Serializable testClassId;
	private static HibernateUtil util;
	private static Session session;
	private static TestClassDaoImpl testClassDao;


	@BeforeClass
	public static void initTest () throws Exception {
		testClassDao = TestClassDaoImpl.getInstance();
		util = HibernateUtil.getInstance();
		session = util.getSession();
	}

	@Before
	public void setUp() throws Exception {
		expectedTestClass = EntityBuilder.buildTestClass("05.06.1992", "06.05.1992", 25);
	}

	private void createEntities() throws Exception {
		testClassId = testClassDao.saveOrUpdate(expectedTestClass);
		expectedTestClass.setId((Integer) testClassId);
	}

	@Test
	public void testGetById() throws Exception {
		createEntities();
		
		delete();
	}


	@After
	public void tearDown () throws Exception {
		expectedTestClass = null;
		expectedTestClass = null;
		testClassId = null;
	}

	@AfterClass
	public static void closeTest() throws Exception{
		session.close();
		util = null;
	}

	private void delete() throws Exception {
		testClassDao.delete((Integer) testClassId);
	}
}
