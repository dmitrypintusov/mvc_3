package by.pvt.pintusov.courses.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Calendar;

/**
 * Describes the entity <strong>Mark</strong>
 * @author pintusov
 * @version 1.1
 */

@Entity
@Table
public class Mark extends AbstractEntity {
	private static final long serialVersionUID = 4L;

	public Mark() { super(); }

	@Column
	public Integer getMark() {
		return mark;
	}
	public void setMark(Integer mark) {
		this.mark = mark;
	}
	private Integer mark;

	@ManyToOne
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	private User user;

	@ManyToOne
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	private Course course;

	@Column
	public Calendar getDate() {
		return date;
	}
	public void setDate(Calendar date) {
		this.date = date;
	}
	private Calendar date;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || !(o instanceof Mark)) return false;
		Mark mark = (Mark) o;
		if (this.mark != null ? !this.mark.equals(mark.mark) : mark.mark != null) return false;
		if (user != null ? !user.equals(mark.user) : mark.user != null) return false;
		if (course != null ? !course.equals(mark.course) : mark.course != null) return false;
		return date != null ? date.equals(mark.date) : mark.date == null;

	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (user != null ? user.hashCode() : 0);
		result = 31 * result + (course != null ? course.hashCode() : 0);
		result = 31 * result + (date != null ? date.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Mark{" +
				"mark=" + mark +
				", user=" + user +
				", course=" + course +
				", date=" + date +
				'}';
	}
}
