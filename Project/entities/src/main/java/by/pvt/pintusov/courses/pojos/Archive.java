package by.pvt.pintusov.courses.pojos;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Describes the entity <strong>Archive</strong>
 * @author pintusov
 * @version 1.1
 */

@Entity
public class Archive extends AbstractEntity {
	private static final long serialVersionUID = 6L;

	public Archive() {super(); }

	@OneToMany (cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public Set<Course> getCourses() {
		return archive;
	}
	public void setCourses(Set<Course> courses) {
		this.archive = courses;
	}
	public void addCourseToArchive(Course course){
		if(archive == null){
			archive = new HashSet<>();
		}
		archive.add(course);
	}
	private Set<Course> archive;

	//TODO: нужно ли equals, HashCode, toString, если одна коллекция
}
