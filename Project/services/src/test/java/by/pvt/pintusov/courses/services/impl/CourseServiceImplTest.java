package by.pvt.pintusov.courses.services.impl;

import by.pvt.pintusov.courses.enums.CourseStatusType;
import by.pvt.pintusov.courses.pojos.Course;
import by.pvt.pintusov.courses.utils.EntityBuilder;
import org.junit.*;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Project: courses
 * Created by: USER
 * Date: 24.01.17.
 */
public class CourseServiceImplTest {
	private Course expectedCourse;
	private Course actualCourse;
	private Serializable courseId;
	private static CourseServiceImpl courseService;

	@BeforeClass
	public static void initTest () throws Exception {
		courseService = CourseServiceImpl.getInstance();
	}

	@Before
	public void setUp() throws Exception {
		expectedCourse = EntityBuilder.buildCourse("TEST", 100, CourseStatusType.OPEN, Calendar.getInstance(), Calendar.getInstance(), null);
	}

	private void createEntities() throws Exception {
		courseId = courseService.saveOrUpdate(expectedCourse);
		expectedCourse.setId((Integer) courseId);
	}

	@Test
	public void testSaveOrUpdate() throws Exception {
		createEntities();
		actualCourse = courseService.getById((Integer) courseId);
		Assert.assertEquals("saveOrUpdate() method failed: ", expectedCourse, actualCourse);
		delete();
	}

	@Test
	public void testGetById() throws Exception {
		createEntities();
		actualCourse = courseService.getById((Integer) courseId);
		Assert.assertEquals("getById() method failed: ", expectedCourse, actualCourse);
		delete();
	}

	@Test
	public void testLoad() throws Exception {
		createEntities();
		actualCourse = courseService.load((Integer) courseId);
		Assert.assertEquals("getById() method failed: ", expectedCourse, actualCourse);
		delete();
	}

	@Test
	public void testDelete () throws Exception {
		createEntities();
		delete();
		actualCourse = courseService.getById((Integer) courseId);
		Assert.assertNull("delete() method failed: ", actualCourse);
	}

	@After
	public void tearDown () throws Exception {
		expectedCourse = null;
		actualCourse = null;
		courseId = null;
	}

	@AfterClass
	public static void closeTest() throws Exception{
		courseService = null;
	}

	private void delete() throws Exception {
		courseService.delete((Integer) courseId);
	}
}
