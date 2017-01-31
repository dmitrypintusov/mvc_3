package by.pvt.pintusov.courses.services.impl;

import by.pvt.pintusov.courses.enums.AccessLevelType;
import by.pvt.pintusov.courses.pojos.AccessLevel;
import by.pvt.pintusov.courses.utils.EntityBuilder;
import by.pvt.pintusov.courses.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.*;

import java.io.Serializable;

/**
 * Project: courses
 * Created by: USER
 * Date: 24.01.17.
 */
public class AccessLevelServiceImplTest {
	private AccessLevel expectedAccessLevel;
	private AccessLevel actualAccessLevel;
	private Serializable accessLevelId;
	private static AccessLevelServiceImpl accessLevelService;

	private static HibernateUtil util;
	private static Session session;

	@BeforeClass
	public static void initTest () throws Exception {
		util = HibernateUtil.getInstance();
		session = util.getSession();
		accessLevelService = AccessLevelServiceImpl.getInstance();
	}

	@Before
	public void setUp() throws Exception {
		expectedAccessLevel = EntityBuilder.buildAccessLevel(AccessLevelType.ADMIN, null);
	}

	private void createEntities() throws Exception {
		accessLevelId = accessLevelService.saveOrUpdate(expectedAccessLevel);
		expectedAccessLevel.setId((Integer) accessLevelId);
	}

	@Test
	public void testSaveOrUpdate() throws Exception {
		createEntities();
		actualAccessLevel = accessLevelService.getById((Integer) accessLevelId);
		Assert.assertEquals("saveOrUpdate() method failed: ", expectedAccessLevel, actualAccessLevel);
		delete();
	}

	@Test
	public void testGetById() throws Exception {
		createEntities();
		actualAccessLevel = accessLevelService.getById((Integer) accessLevelId);
		Assert.assertEquals("getById() method failed: ", expectedAccessLevel, actualAccessLevel);
		delete();
	}

	@Test
	public void testLoad() throws Exception {
		createEntities();
		actualAccessLevel = accessLevelService.load((Integer) accessLevelId);
		Assert.assertEquals("getById() method failed: ", expectedAccessLevel, actualAccessLevel);
		delete();
	}

	@Test
	public void testDelete () throws Exception {
		createEntities();
		delete();
		actualAccessLevel = accessLevelService.getById((Integer) accessLevelId);
		Assert.assertNull("delete() method failed: ", actualAccessLevel);
	}

	@After
	public void tearDown () throws Exception {
		expectedAccessLevel = null;
		actualAccessLevel = null;
		accessLevelId = null;
	}

	@AfterClass
	public static void closeTest() throws Exception{
		accessLevelService = null;
		util = null;
	}

	private void delete() throws Exception {
		accessLevelService.delete((Integer) accessLevelId);
	}
}
