package by.pvt.pintusov.courses.dao.impl;

import by.pvt.pintusov.courses.dao.Impl.MarkDaoImpl;
import by.pvt.pintusov.courses.pojos.Mark;
import by.pvt.pintusov.courses.utils.EntityBuilder;
import by.pvt.pintusov.courses.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.*;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Project: courses
 * Created by: USER
 * Date: 25.01.17.
 */
public class MarkDaoImplTest {
	private Mark expectedMark;
	private Mark actualMark;
	private Serializable markId;
	private Transaction transaction;

	private static HibernateUtil util;
	private static Session session;
	private static SessionFactory sessionFactory;
	private static MarkDaoImpl markDao;

	@BeforeClass
	public static void initTest () throws Exception {
		util = HibernateUtil.getInstance();
		session = util.getSession();
		sessionFactory = util.getSessionFactory();
		markDao = MarkDaoImpl.getInstance(sessionFactory);
	}

	@Before
	public void setUp() throws Exception {
		expectedMark = EntityBuilder.buildMark(100, null, null, Calendar.getInstance());
		transaction = sessionFactory.getCurrentSession().beginTransaction();
	}

	@Test
	public void testSaveOrUpdate() throws Exception {
		createEntities();
		actualMark = markDao.getById((Integer) markId);
		Assert.assertEquals("saveOrUpdate() method failed: ", expectedMark, actualMark);
		delete();
	}

	@Test
	public void testGetById() throws Exception {
		createEntities();
		actualMark = markDao.getById((Integer) markId);
		Assert.assertEquals("getById() method failed: ", expectedMark, actualMark);
		delete();
	}

	@Test
	public void testLoad () throws Exception {
		createEntities();
		actualMark = markDao.load((Integer) markId);
		Assert.assertEquals("load() method failed: ", expectedMark, actualMark);
		delete();
	}

	@Test
	public void testDelete () throws Exception {
		createEntities();
		delete();
		actualMark = markDao.getById((Integer) markId);
		Assert.assertNull("delete() method failed: ", actualMark);
	}

	@After
	public void tearDown () throws Exception {
		sessionFactory.getCurrentSession().getTransaction().commit();
		expectedMark = null;
		actualMark = null;
		markId = null;
		transaction = null;
	}

	@AfterClass
	public static void closeTest() throws Exception{
		markDao = null;
		util = null;
	}

	private void createEntities() throws Exception {
		markId = markDao.saveOrUpdate(expectedMark);
		expectedMark.setId((Integer) markId);
	}

	private void delete() throws Exception {
		markDao.delete((Integer) markId);
	}
}
