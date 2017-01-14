package by.pvt.pintusov.courses.services.impl;

import by.pvt.pintusov.courses.constants.UserType;
import by.pvt.pintusov.courses.dao.Impl.CourseDaoImpl;
import by.pvt.pintusov.courses.dao.Impl.UserDaoImpl;
import by.pvt.pintusov.courses.entities.Course;
import by.pvt.pintusov.courses.entities.Operation;
import by.pvt.pintusov.courses.entities.User;
import by.pvt.pintusov.courses.utils.EntityBuilder;
import org.junit.*;

/**
 * Created by USER on 09.01.17.
 */
public class UserServiceImplTest {
	private static User user;

	@BeforeClass
	public static void setUp() throws Exception {
		user = EntityBuilder.buildUser("TEST", "TEST", "TEST", "TEST", 0);
		UserServiceImpl.getInstance().add(user);
	}

	@AfterClass
	public static void tearDown() throws Exception{
		UserServiceImpl.getInstance().deleteByLogin(user.getLogin());
		user = null;
	}

	@Test
	public void testGetInstance() throws Exception {
		UserServiceImpl instance1 = UserServiceImpl.getInstance();
		UserServiceImpl instance2 = UserServiceImpl.getInstance();
		Assert.assertEquals(instance1.hashCode(), instance2.hashCode());
	}

	@Test
	public void testCheckUserAuthorization() throws Exception {
		boolean actual = UserServiceImpl.getInstance().checkUserAuthorization(user.getLogin(), user.getPassword());
		Assert.assertTrue(actual);
	}

	@Test
	public void testGetUserByLogin() throws Exception {
		User actual = UserServiceImpl.getInstance().getUserByLogin(user.getLogin());
		Assert.assertEquals(user, actual);
	}

	@Test
	public void testCheckAccessType() throws Exception {
		UserType actual = UserServiceImpl.getInstance().checkAccessType(user);
		Assert.assertEquals(user.getAccessType(), actual.ordinal());
	}

	@Test
	public void testCheckIsNewUser() throws Exception {
		boolean actual = UserServiceImpl.getInstance().checkIsNewUser(user.getLogin());
		Assert.assertFalse(actual);
	}
}
