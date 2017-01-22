package by.pvt.pintusov.courses.pojos;

import lombok.Data;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Describes the entity <strong>User</strong>
 * @author pintusov
 * @version 1.1
 */

//@Data
@Entity
@Table
public class User extends AbstractEntity {
	private static final long serialVersionUID = 2L;

	public User() { super (); }

	@Column (nullable = false)
	public String getFirstName () { return firstName; }
	public void setFirstName (String firstName) { this.firstName = firstName; }
	private String firstName;

	@Column (nullable = false)
	public String getLastName () { return lastName; }
	public void setLastName (String lastName) { this.lastName = lastName; }
	private String lastName;

	@Access (AccessType.FIELD)
	//TODO: проверить синтаксис. слетают тесты в service
	//@Formula(value = "AS CONCAT (COALESCE(name, ''), COALESCE(' ' + surname, ''))")
	private String fullName;
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	@Column (nullable = false)
	public String getLogin () { return login; }
	public void setLogin (String login) { this.login = login; }
	private String login;

	@Column (nullable = false)
	public String getPassword () { return password; }
	public void setPassword (String password) { this.password = password; }
	private String password;

	@ManyToMany (cascade = CascadeType.ALL)
	public Set<Course> getCourses() {
		return courses;
	}
	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}
	public void addCourse(Course course){
		if(courses == null){
			courses = new HashSet<>();
		}
		courses.add(course);
	}
	private Set <Course> courses;

	@OneToMany (cascade = CascadeType.ALL)
	public Set<Mark> getMarks() {
		return marks;
	}
	public void setMarks(Set<Mark> marks) {
		this.marks = marks;
	}
	public void addMark(Mark mark){
		if(marks == null){
			marks = new HashSet<>();
		}
		marks.add(mark);
	}
	private Set<Mark> marks;

	@ManyToMany (cascade = CascadeType.ALL)
	public Set<AccessLevel> getAccessLevels() {
		return accessLevels;
	}
	public void setAccessLevels(Set<AccessLevel> accessLevels) {
		this.accessLevels = accessLevels;
	}
	public void addAccessLevel(AccessLevel accessLevel){
		if(accessLevels == null){
			accessLevels = new HashSet<>();
		}
		accessLevels.add(accessLevel);
	}
	private Set<AccessLevel> accessLevels;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || !(o instanceof User)) return false;
		if (!super.equals(o)) return false;
		User user = (User) o;
		if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
		if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
		if (login != null ? !login.equals(user.login) : user.login != null) return false;
		return password != null ? password.equals(user.password) : user.password == null;

	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
		result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
		result = 31 * result + (login != null ? login.hashCode() : 0);
		result = 31 * result + (password != null ? password.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "User{" +
				"firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", login='" + login + '\'' +
				", password='" + password + '\'' +
				'}';
	}
}
