package by.pvt.pintusov.courses.services.impl;

import by.pvt.pintusov.courses.pojos.Archive;
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
public class ArchiveServiceImplTest {
	private Archive expectedArchive;
	private Archive actualAccessLevel;
	private Serializable archiveId;
	private static ArchiveServiceImpl archiveService;

	private static HibernateUtil util;
	private static Session session;
	private static SessionFactory sessionFactory;

	@BeforeClass
	public static void initTest () throws Exception {
		util = HibernateUtil.getInstance();
		session = util.getSession();
		sessionFactory = util.getSessionFactory();
		archiveService = ArchiveServiceImpl.getInstance(sessionFactory);
	}

	@Before
	public void setUp() throws Exception {
		expectedArchive = EntityBuilder.buildArchive(null);
	}

	private void createEntities() throws Exception {
		archiveId = archiveService.saveOrUpdate(expectedArchive);
		expectedArchive.setId((Integer) archiveId);
	}

	@Test
	public void testSaveOrUpdate() throws Exception {
		createEntities();
		actualAccessLevel = archiveService.getById((Integer) archiveId);
		Assert.assertEquals("saveOrUpdate() method failed: ", expectedArchive, actualAccessLevel);
		delete();
	}

	@Test
	public void testGetById() throws Exception {
		createEntities();
		actualAccessLevel = archiveService.getById((Integer) archiveId);
		Assert.assertEquals("getById() method failed: ", expectedArchive, actualAccessLevel);
		delete();
	}

	@Ignore
	@Test
	public void testLoad() throws Exception {
		createEntities();
		actualAccessLevel = archiveService.load((Integer) archiveId);
		Assert.assertEquals("getById() method failed: ", expectedArchive, actualAccessLevel);
		delete();
	}

	@Test
	public void testDelete () throws Exception {
		createEntities();
		delete();
		actualAccessLevel = archiveService.getById((Integer) archiveId);
		Assert.assertNull("delete() method failed: ", actualAccessLevel);
	}

	@After
	public void tearDown () throws Exception {
		expectedArchive = null;
		actualAccessLevel = null;
		archiveId = null;
	}

	@AfterClass
	public static void closeTest() throws Exception{
		archiveService = null;
		util = null;
		//session.close();
	}

	private void delete() throws Exception {
		archiveService.delete((Integer) archiveId);
	}
}
