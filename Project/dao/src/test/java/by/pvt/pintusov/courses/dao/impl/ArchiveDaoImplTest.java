package by.pvt.pintusov.courses.dao.impl;

import by.pvt.pintusov.courses.dao.Impl.ArchiveDaoImpl;
import by.pvt.pintusov.courses.pojos.Archive;
import by.pvt.pintusov.courses.utils.EntityBuilder;
import by.pvt.pintusov.courses.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.*;

import java.io.Serializable;

/**
 * Project: courses
 * Created by: USER
 * Date: 25.01.17.
 */
public class ArchiveDaoImplTest {
	private Archive expectedArchive;
	private Archive actualArchive;
	private Serializable archiveId;
	private Transaction transaction;

	private static HibernateUtil util;
	private static Session session;
	private static ArchiveDaoImpl archiveDao;

	@BeforeClass
	public static void initTest () throws Exception {
		archiveDao = ArchiveDaoImpl.getInstance();
		util = HibernateUtil.getInstance();
		session = util.getSession();
	}

	@Before
	public void setUp() throws Exception {
		expectedArchive = EntityBuilder.buildArchive(null);
		transaction = session.beginTransaction();
	}

	@Test
	public void testSaveOrUpdate() throws Exception {
		createEntities();
		actualArchive = archiveDao.getById((Integer) archiveId);
		Assert.assertEquals("saveOrUpdate() method failed: ", expectedArchive, actualArchive);
		delete();
	}

	@Test
	public void testGetById() throws Exception {
		createEntities();
		actualArchive = archiveDao.getById((Integer) archiveId);
		Assert.assertEquals("getById() method failed: ", expectedArchive, actualArchive);
		delete();
	}

	@Test
	public void testLoad () throws Exception {
		createEntities();
		actualArchive = archiveDao.load((Integer) archiveId);
		Assert.assertEquals("load() method failed: ", expectedArchive, actualArchive);
		delete();
	}

	@Test
	public void testDelete () throws Exception {
		createEntities();
		delete();
		actualArchive = archiveDao.getById((Integer) archiveId);
		Assert.assertNull("delete() method failed: ", actualArchive);
	}

	@After
	public void tearDown () throws Exception {
		transaction.commit();
		expectedArchive = null;
		actualArchive = null;
		archiveId = null;
		transaction = null;
	}

	@AfterClass
	public static void closeTest() throws Exception{
		archiveDao = null;
		util = null;
		//session.close();
	}

	private void createEntities() throws Exception {
		archiveId = archiveDao.saveOrUpdate(expectedArchive);
		expectedArchive.setId((Integer) archiveId);
	}

	private void delete() throws Exception {
		archiveDao.delete((Integer) archiveId);
	}
}
