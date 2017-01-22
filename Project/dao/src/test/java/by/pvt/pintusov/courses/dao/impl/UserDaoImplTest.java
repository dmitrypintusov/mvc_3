package by.pvt.pintusov.courses.dao.impl;

import by.pvt.pintusov.courses.dao.Impl.UserDaoImpl;
import by.pvt.pintusov.courses.pojos.User;
import by.pvt.pintusov.courses.utils.EntityBuilder;
import by.pvt.pintusov.courses.utils.HibernateUtil;
import org.hibernate.Session;
import org.junit.*;

import java.io.Serializable;

/**
 * Test User Dao
 * @author pintusov
 * @version 1.1
 */

public class UserDaoImplTest {
	private User expectedUser;
	private User actualUser;
	private Serializable userId;
	private static HibernateUtil util;
	private static Session session;
	private static UserDaoImpl userDao;

	@BeforeClass
	public static void initTest () throws Exception {
		userDao = UserDaoImpl.getInstance();
		util = HibernateUtil.getInstance();
		session = util.getSession();
	}

	@Before
	public void setUp() throws Exception {
		expectedUser = EntityBuilder.buildUser("TEST", "TEST", "TEST", "TEST", null, null, null);
	}

	private void createEntities() throws Exception {
		userId = userDao.saveOrUpdate(expectedUser);
		expectedUser.setId((Integer) userId);
	}

	@Test
	public void testSaveOrUpdate() throws Exception {
		createEntities();
		actualUser = userDao.getById((Integer) userId);
		Assert.assertEquals("saveOrUpdate() method failed: ", expectedUser, actualUser);
		delete();
	}

	@Test
	public void testGetById() throws Exception {
		createEntities();
		actualUser = userDao.getById((Integer) userId);
		Assert.assertEquals("getById() method failed: ", expectedUser, actualUser);
		delete();
	}

	@Test
	public void testLoad () throws Exception {
		createEntities();
		actualUser = userDao.load((Integer) userId);
		Assert.assertEquals("load() method failed: ", expectedUser, actualUser);
		delete();
	}

	@Ignore
	@Test
	public void testGetByLogin () throws Exception {
		createEntities();
		actualUser = userDao.getUserByLogin(expectedUser.getLogin());
		Assert.assertEquals("getByLogin() method failed: ", expectedUser, actualUser);
		delete();
	}

	@Test
	public void testDelete () throws Exception {
		createEntities();
		delete();
		actualUser = userDao.getById((Integer) userId);
		Assert.assertNull("delete() method failed: ", actualUser);
	}

	@After
	public void tearDown () throws Exception {
		expectedUser = null;
		actualUser = null;
		userId = null;
	}

	@AfterClass
	public static void closeTest() throws Exception{
		session.close();
		util = null;
		userDao = null;
	}

	private void delete() throws Exception {
		userDao.delete((Integer) userId);
	}
}
