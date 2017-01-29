package by.pvt.pintusov.courses.services.impl;

import by.pvt.pintusov.courses.pojos.User;
import by.pvt.pintusov.courses.utils.EntityBuilder;
import by.pvt.pintusov.courses.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.*;

import java.io.Serializable;

/**
 * Created by USER on 09.01.17.
 */
public class UserServiceImplTest {
	private User expectedUser;
	private User actualUser;
	private Serializable userId;
	private static UserServiceImpl userService;

	private static HibernateUtil util;
	private static Session session;
	private static Session currentSession;
	private static SessionFactory sessionFactory;

	@BeforeClass
	public static void initTest () throws Exception {
		util = HibernateUtil.getInstance();
		session = util.getSession();
		sessionFactory = util.getSessionFactory();
		currentSession = sessionFactory.getCurrentSession();
		userService = UserServiceImpl.getInstance(sessionFactory);
	}

	@Before
	public void setUp() throws Exception {
		expectedUser = EntityBuilder.buildUser("TEST", "TEST", "TEST", "TEST", null, null, null);
	}

	private void createEntities() throws Exception {
		userId = userService.saveOrUpdate(expectedUser);
		expectedUser.setId((Integer) userId);
	}

	@Test
	public void testSaveOrUpdate() throws Exception {
		createEntities();
		actualUser = userService.getById((Integer) userId);
		Assert.assertEquals("saveOrUpdate() method failed: ", expectedUser, actualUser);
		delete();
	}

	@Test
	public void testGetById() throws Exception {
		createEntities();
		actualUser = userService.getById((Integer) userId);
		Assert.assertEquals("getById() method failed: ", expectedUser, actualUser);
		delete();
	}

	@Test
	public void testLoad() throws Exception {
		createEntities();
		actualUser = userService.load((Integer) userId);
		Assert.assertEquals("getById() method failed: ", expectedUser, actualUser);
		delete();
	}

	@Test
	public void testDelete () throws Exception {
		createEntities();
		delete();
		actualUser = userService.getById((Integer) userId);
		Assert.assertNull("delete() method failed: ", actualUser);
	}

	//@Ignore
	@Test
	public void testIsAuthorized () throws Exception {
		createEntities();
		boolean authorized = userService.checkUserAuthorization(expectedUser.getLogin(), expectedUser.getPassword());
		Assert.assertTrue("UserAuthorization() method failed: ", authorized);
		delete();
	}

	@After
	public void tearDown () throws Exception {
		expectedUser = null;
		actualUser = null;
		userId = null;
	}

	@AfterClass
	public static void closeTest() throws Exception{
		userService = null;
	}

	private void delete() throws Exception {
		userService.delete((Integer) userId);
	}
}
