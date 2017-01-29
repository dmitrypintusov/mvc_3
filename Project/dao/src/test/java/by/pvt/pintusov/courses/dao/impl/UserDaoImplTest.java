package by.pvt.pintusov.courses.dao.impl;

import by.pvt.pintusov.courses.dao.Impl.AccessLevelDaoImpl;
import by.pvt.pintusov.courses.dao.Impl.CourseDaoImpl;
import by.pvt.pintusov.courses.dao.Impl.MarkDaoImpl;
import by.pvt.pintusov.courses.dao.Impl.UserDaoImpl;
import by.pvt.pintusov.courses.enums.AccessLevelType;
import by.pvt.pintusov.courses.enums.CourseStatusType;
import by.pvt.pintusov.courses.pojos.AccessLevel;
import by.pvt.pintusov.courses.pojos.Course;
import by.pvt.pintusov.courses.pojos.Mark;
import by.pvt.pintusov.courses.pojos.User;
import by.pvt.pintusov.courses.utils.EntityBuilder;
import by.pvt.pintusov.courses.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.*;

import java.io.Serializable;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

/**
 * Test User Dao
 * @author pintusov
 * @version 1.1
 */

public class UserDaoImplTest {
	private User expectedUser;
	private User actualUser;
	private Course course;
	private Mark mark;
	private AccessLevel accessLevel;
	private Serializable userId;
	private Serializable courseId;
	private Serializable markId;
	private Serializable accessLevelId;

	private Set<User> users;
	private Set<Course> courses;
	private Set<AccessLevel> accessLevels;
	private Set<Mark> marks;

	private static HibernateUtil util;
	private static Session session;
	private static SessionFactory sessionFactory;
	private Transaction transaction;

	private static UserDaoImpl userDao;
	private static CourseDaoImpl courseDao;
	private static MarkDaoImpl markDao;
	private static AccessLevelDaoImpl accessLevelDao;

	@BeforeClass
	public static void initTest () throws Exception {
		util = HibernateUtil.getInstance();
		session = util.getSession();
		sessionFactory = util.getSessionFactory();

		userDao = UserDaoImpl.getInstance(sessionFactory);
		courseDao = CourseDaoImpl.getInstance(sessionFactory);
		markDao = MarkDaoImpl.getInstance(sessionFactory);
		accessLevelDao = AccessLevelDaoImpl.getInstance(sessionFactory);
	}

	@Before
	public void setUp() throws Exception {
		courses = new HashSet<>();
		accessLevels = new HashSet<>();
		marks = new HashSet<>();
		users = new HashSet<>();

		expectedUser = EntityBuilder.buildUser("TEST", "TEST", "TEST", "TEST", null, null, null);
		users.add(expectedUser);

		course = EntityBuilder.buildCourse("COURSE", 100, CourseStatusType.OPEN, Calendar.getInstance(), Calendar.getInstance(), users);
		courses.add(course);
		expectedUser.setCourses(courses);

		mark = EntityBuilder.buildMark(10, expectedUser, course, Calendar.getInstance());
		marks.add(mark);
		expectedUser.setMarks(marks);

		accessLevel = EntityBuilder.buildAccessLevel(AccessLevelType.STUDENT, users);
		accessLevels.add(accessLevel);
		expectedUser.setAccessLevels(accessLevels);
		transaction = sessionFactory.getCurrentSession().beginTransaction();
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

	@Test
	public void testGetByLogin () throws Exception {
		createEntities();
		actualUser = userDao.getUserByLogin(expectedUser.getLogin());
		Assert.assertEquals("getByLogin() method failed: ", expectedUser, actualUser);
		delete();
	}

	@Test
	public void testIsAuthorized () throws Exception {
		createEntities();
		boolean isAuthorized;
		isAuthorized = userDao.isAuthorized(expectedUser.getLogin(), expectedUser.getPassword());
		Assert.assertTrue("isAuthorized() method failed: ", isAuthorized);
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
		sessionFactory.getCurrentSession().getTransaction().commit();
		expectedUser = null;
		actualUser = null;
		course = null;
		mark = null;
		accessLevel = null;
		userId = null;
		courseId = null;
		markId = null;
		accessLevelId = null;
		transaction = null;
	}

	@AfterClass
	public static void closeTest() throws Exception{
		userDao = null;
		courseDao = null;
		markDao = null;
		accessLevelDao = null;
		util = null;
		session.close();
	}

	private void createEntities() throws Exception {
		accessLevelId = accessLevelDao.saveOrUpdate(accessLevel);
		markId = markDao.saveOrUpdate(mark);
		userId = userDao.saveOrUpdate(expectedUser);
		courseId = courseDao.saveOrUpdate(course);
		expectedUser.setId((Integer) userId);
	}

	private void delete() throws Exception {
		accessLevelDao.delete((Integer) accessLevelId);
		markDao.delete((Integer) markId);
		userDao.delete((Integer) userId);
	}
}
