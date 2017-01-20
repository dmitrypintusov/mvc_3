package by.pvt.pintusov.courses.pojos;

import by.pvt.pintusov.courses.enums.CourseStatusType;

import javax.persistence.*;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

/**
 * Describes the entity <strong>Course</strong>
 * @author pintusov
 * @version 1.1
 */

@Entity
@Table
public class Course extends AbstractEntity {
	private static final long serialVersionUID = 3L;

	public Course() {
		super();
	}

	@Column
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	private String courseName;

	@Column
	public Integer getHours() {
		return hours;
	}
	public void setHours(Integer hours) {
		this.hours = hours;
	}
	private Integer hours;

	@Enumerated(EnumType.STRING)
	@Column
	public CourseStatusType getCourseStatus() {
		return courseStatus;
	}
	public void setCourseStatus(CourseStatusType courseStatus) {
		this.courseStatus = courseStatus;
	}
	private CourseStatusType courseStatus;

	@Column
	public Calendar getDate() {
		return date;
	}
	public void setDate(Calendar date) {
		this.date = date;
	}
	private Calendar date;

	@ManyToMany
	public Set<User> getUser() {
		return users;
	}
	public void setUser(Set<User> users) {
		this.users = users;
	}
	public void addUser(User user){
		if(users == null){
			users = new HashSet<>();
		}
		users.add(user);
	}
	private Set<User> users;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || !(o instanceof User)) return false;
		if (!super.equals(o)) return false;
		Course course = (Course) o;
		if (courseName != null ? !courseName.equals(course.courseName) : course.courseName != null) return false;
		return hours != null ? hours.equals(course.hours) : course.hours == null;

	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (courseName != null ? courseName.hashCode() : 0);
		result = 31 * result + (hours != null ? hours.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Course{" +
				"courseName='" + courseName + '\'' +
				", hours=" + hours +
				", courseStatus=" + courseStatus +
				", date=" + date +
				'}';
	}
}
