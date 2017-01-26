package by.pvt.pintusov.courses.dao.impl.InheritanceTest;

import by.pvt.pintusov.courses.enheritance.Person;
import by.pvt.pintusov.courses.enheritance.Student;
import by.pvt.pintusov.courses.enheritance.Teacher;
import by.pvt.pintusov.courses.inheritanceDAO.InheritanceDaoImpl;
import by.pvt.pintusov.courses.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.*;

/**
 * Project name: courses
 * Created by Дмитрий
 * Date: 23.01.2017.
 */
public class InheritanceDaoTest {
	private Person expectedPerson;
	private Person actualPerson;
	private Integer personId;

	private Teacher expectedTeacher;
	private Teacher actualTeacher;
	private Integer teacherId;

	private Student expectedStudent;
	private Student actualStudent;
	private Integer studentId;

	private HibernateUtil util;
	private Session session;
	private Transaction transaction;

	private InheritanceDaoImpl inheritanceDao;


	@Before
	public void initTest () throws Exception {
		inheritanceDao = InheritanceDaoImpl.getInstance();
		util = HibernateUtil.getInstance();
		session = util.getSession();
		transaction = session.beginTransaction();
	}

	@Test
	public void testSaveOrUpdate() throws Exception {
		expectedPerson = new Person();
		expectedPerson.setName("TEST");
		expectedPerson.setSurname("TEST");

		expectedStudent = new Student();
		expectedStudent.setName("S_TEST");
		expectedStudent.setSurname("S_TEST");
		expectedStudent.setFaculty("S_TEST");
		expectedStudent.setMark(10.0);

		expectedTeacher = new Teacher();
		expectedTeacher.setName("T_TEST");
		expectedTeacher.setSurname("T_TEST");
		expectedTeacher.setPosition("T_TEST");
		expectedTeacher.setSalary(100L);

		personId = inheritanceDao.saveOrUpdate(expectedPerson);
		expectedPerson.setId(personId);
		actualPerson = inheritanceDao.getById(personId);
		Assert.assertEquals("saveOrUpdate() method failed: ", expectedPerson, actualPerson);

		teacherId = inheritanceDao.saveOrUpdate(expectedTeacher);
		expectedTeacher.setId(teacherId);
		actualTeacher = (Teacher) inheritanceDao.getById(teacherId);
		Assert.assertEquals("saveOrUpdate() method failed: ",expectedTeacher, actualTeacher);

		studentId = inheritanceDao.saveOrUpdate(expectedStudent);
		expectedStudent.setId(studentId);
		actualStudent = (Student) inheritanceDao.getById(studentId);
		Assert.assertEquals("saveOrUpdate() method failed: ", expectedStudent, actualStudent);
	}

	@After
	public void closeTest() throws Exception{
		transaction.commit();
		inheritanceDao = null;
		util = null;
		//session.close();
	}
}
