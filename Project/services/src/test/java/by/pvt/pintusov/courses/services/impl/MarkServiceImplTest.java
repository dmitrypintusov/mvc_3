package by.pvt.pintusov.courses.services.impl;

import by.pvt.pintusov.courses.pojos.Mark;
import by.pvt.pintusov.courses.utils.EntityBuilder;
import org.hibernate.SessionFactory;
import org.junit.*;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Project: courses
 * Created by: USER
 * Date: 24.01.17.
 */
public class MarkServiceImplTest {
	private Mark expectedMark;
	private Mark actualMark;
	private Serializable markId;
	private static MarkServiceImpl markService;
	private static SessionFactory sessionFactory;

	@BeforeClass
	public static void initTest () throws Exception {
		markService = MarkServiceImpl.getInstance(sessionFactory);
	}

	@Before
	public void setUp() throws Exception {
		expectedMark = EntityBuilder.buildMark(10, null, null, Calendar.getInstance());
	}

	private void createEntities() throws Exception {
		markId = markService.saveOrUpdate(expectedMark);
		expectedMark.setId((Integer) markId);
	}

	@Test
	public void testSaveOrUpdate() throws Exception {
		createEntities();
		actualMark = markService.getById((Integer) markId);
		Assert.assertEquals("saveOrUpdate() method failed: ", expectedMark, actualMark);
		delete();
	}

	@Test
	public void testGetById() throws Exception {
		createEntities();
		actualMark = markService.getById((Integer) markId);
		Assert.assertEquals("getById() method failed: ", expectedMark, actualMark);
		delete();
	}

	@Test
	public void testLoad() throws Exception {
		createEntities();
		actualMark = markService.load((Integer) markId);
		Assert.assertEquals("getById() method failed: ", expectedMark, actualMark);
		delete();
	}

	@Test
	public void testDelete () throws Exception {
		createEntities();
		delete();
		actualMark = markService.getById((Integer) markId);
		Assert.assertNull("delete() method failed: ", actualMark);
	}

	@After
	public void tearDown () throws Exception {
		expectedMark = null;
		actualMark = null;
		markId = null;
	}

	@AfterClass
	public static void closeTest() throws Exception{
		markService = null;
	}

	private void delete() throws Exception {
		markService.delete((Integer) markId);
	}
}
