package by.pvt.pintusov.courses.dao.impl;

import by.pvt.pintusov.courses.dao.Impl.AccessLevelDaoImpl;
import by.pvt.pintusov.courses.enums.AccessLevelType;
import by.pvt.pintusov.courses.pojos.AccessLevel;
import by.pvt.pintusov.courses.utils.EntityBuilder;
import by.pvt.pintusov.courses.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.*;

import java.io.Serializable;

/**
 * Project: courses
 * Created by: USER
 * Date: 25.01.17.
 */
public class AccessLevelDaoImplTest {
	private AccessLevel expectedAccessLevel;
	private AccessLevel actualAccessLevel;
	private Serializable accessLevelId;
	private Transaction transaction;

	private static HibernateUtil util;
	private static Session session;
	private static AccessLevelDaoImpl accessLevelDao;

	@BeforeClass
	public static void initTest () throws Exception {
		util = HibernateUtil.getInstance();
		session = util.getSession();
		accessLevelDao = AccessLevelDaoImpl.getInstance();
	}

	@Before
	public void setUp() throws Exception {
		expectedAccessLevel = EntityBuilder.buildAccessLevel(AccessLevelType.ADMIN, null);
		transaction = session.beginTransaction();
	}

	@Test
	public void testSaveOrUpdate() throws Exception {
		createEntities();
		actualAccessLevel = accessLevelDao.getById((Integer) accessLevelId);
		Assert.assertEquals("saveOrUpdate() method failed: ", expectedAccessLevel, actualAccessLevel);
		delete();
	}

	@Test
	public void testGetById() throws Exception {
		createEntities();
		actualAccessLevel = accessLevelDao.getById((Integer) accessLevelId);
		Assert.assertEquals("getById() method failed: ", expectedAccessLevel, actualAccessLevel);
		delete();
	}

	@Test
	public void testLoad () throws Exception {
		createEntities();
		actualAccessLevel = accessLevelDao.load((Integer) accessLevelId);
		Assert.assertEquals("load() method failed: ", expectedAccessLevel, actualAccessLevel);
		delete();
	}

	@Test
	public void testDelete () throws Exception {
		createEntities();
		delete();
		actualAccessLevel = accessLevelDao.getById((Integer) accessLevelId);
		Assert.assertNull("delete() method failed: ", actualAccessLevel);
	}

	@After
	public void tearDown () throws Exception {
		session.getTransaction().commit();
		expectedAccessLevel = null;
		actualAccessLevel = null;
		accessLevelId = null;
		transaction = null;
	}

	@AfterClass
	public static void closeTest() throws Exception{
		accessLevelDao = null;
		util = null;
	}

	private void createEntities() throws Exception {
		accessLevelId = accessLevelDao.saveOrUpdate(expectedAccessLevel);
		expectedAccessLevel.setId((Integer) accessLevelId);
	}

	private void delete() throws Exception {
		accessLevelDao.delete((Integer) accessLevelId);
	}
}
