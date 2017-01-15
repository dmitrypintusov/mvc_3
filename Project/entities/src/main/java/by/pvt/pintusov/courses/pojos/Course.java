package by.pvt.pintusov.courses.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Describes the entity <strong>Course</strong>
 * @author pintusov
 * @version 1.1
 */

@Entity
@Table
public class Course extends AbstractEntity {
	private static final long serialVersionUID = 2L;

	@Column
	private String courseName;
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	@Column
	private Integer hours;
	public Integer getHours() {
		return hours;
	}
	public void setHours(Integer hours) {
		this.hours = hours;
	}

	@Column
	private Integer status;
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || !(o instanceof Course)) return false;
		if (!super.equals(o)) return false;
		Course other = (Course) o;
		if (hours != other.hours) return false;
		if (status != other.status) return false;
		return courseName.equals(other.courseName);
	}

	@Override
	public int hashCode() {
		final int alfa = 31;
		int result = super.hashCode();
		result = alfa * result + courseName.hashCode();
		result = alfa * result + hours;
		result = alfa * result + status;
		return result;
	}

	@Override
	public String toString() {
		return "Course {courseName=" + courseName + ", hours=" + hours + ", status=" + status + "}";
	}
}
