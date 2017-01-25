package by.pvt.pintusov.courses.dao.impl;

import by.pvt.pintusov.courses.dao.Impl.CourseDaoImpl;
import by.pvt.pintusov.courses.enums.CourseStatusType;
import by.pvt.pintusov.courses.pojos.Course;
import by.pvt.pintusov.courses.utils.EntityBuilder;
import by.pvt.pintusov.courses.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.*;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Project: courses
 * Created by: USER
 * Date: 25.01.17.
 */
public class CourseDaoImplTest {
	private Course expectedCourse;
	private Course actualCourse;
	private Serializable courseId;
	private Transaction transaction;

	private static HibernateUtil util;
	private static Session session;
	private static CourseDaoImpl courseDao;

	@BeforeClass
	public static void initTest () throws Exception {
		courseDao = CourseDaoImpl.getInstance();
		util = HibernateUtil.getInstance();
		session = util.getSession();
	}

	@Before
	public void setUp() throws Exception {
		expectedCourse = EntityBuilder.buildCourse("TEST", 100, CourseStatusType.OPEN, Calendar.getInstance(), Calendar.getInstance(), null);
		transaction = session.beginTransaction();
	}

	@Test
	public void testSaveOrUpdate() throws Exception {
		createEntities();
		actualCourse = courseDao.getById((Integer) courseId);
		Assert.assertEquals("saveOrUpdate() method failed: ", expectedCourse, actualCourse);
		delete();
	}

	@Test
	public void testGetById() throws Exception {
		createEntities();
		actualCourse = courseDao.getById((Integer) courseId);
		Assert.assertEquals("getById() method failed: ", expectedCourse, actualCourse);
		delete();
	}

	@Test
	public void testLoad () throws Exception {
		createEntities();
		actualCourse = courseDao.load((Integer) courseId);
		Assert.assertEquals("load() method failed: ", expectedCourse, actualCourse);
		delete();
	}

	@Test
	public void testDelete () throws Exception {
		createEntities();
		delete();
		actualCourse = courseDao.getById((Integer) courseId);
		Assert.assertNull("delete() method failed: ", actualCourse);
	}

	@After
	public void tearDown () throws Exception {
		transaction.commit();
		expectedCourse = null;
		actualCourse = null;
		courseId = null;
		transaction = null;
	}

	@AfterClass
	public static void closeTest() throws Exception{
		courseDao = null;
		util = null;
		//session.close();
	}

	private void createEntities() throws Exception {
		courseId = courseDao.saveOrUpdate(expectedCourse);
		expectedCourse.setId((Integer) courseId);
	}

	private void delete() throws Exception {
		courseDao.delete((Integer) courseId);
	}
}
