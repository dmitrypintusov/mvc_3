package by.pvt.pintusov.courses.services.impl;

import by.pvt.pintusov.courses.pojos.User;
import by.pvt.pintusov.courses.services.IUserService;
import by.pvt.pintusov.courses.utils.EntityBuilder;
import by.pvt.pintusov.courses.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.Serializable;

/**
 * Created by USER on 09.01.17.
 */
@ContextConfiguration ("/test-services-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceImplTest {

	@Autowired
	private IUserService userService;

	private User expectedUser;
	private User actualUser;
	private Serializable userId;

	@Before
	public void setUp() throws Exception {
		expectedUser = EntityBuilder.buildUser("TEST", "TEST", "TEST", "TEST", null, null, null);
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
	public void testDelete () throws Exception {
		createEntities();
		delete();
		actualUser = userService.getById((Integer) userId);
		Assert.assertNull("delete() method failed: ", actualUser);
	}

	@After
	public void tearDown () throws Exception {
		expectedUser = null;
		actualUser = null;
		userId = null;
		userService = null;
	}

	private void createEntities() throws Exception {
		userId = userService.saveOrUpdate(expectedUser);
		expectedUser.setId((Integer) userId);
	}

	private void delete() throws Exception {
		userService.delete((Integer) userId);
	}
}
