package by.pvt.pintusov.courses.pojos;

import by.pvt.pintusov.courses.enums.AccessLevelType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Describes the entity <strong>AccessLevel</strong>
 * @author pintusov
 * @version 1.1
 */
@Entity
@Table
public class AccessLevel extends AbstractEntity {
	private static final long serialVersionUID = 5L;

	public AccessLevel() {super();}

	@Enumerated (EnumType.STRING)
	@Column(columnDefinition = "enum('TEACHER', 'USER', 'ADMIN')")
	public AccessLevelType getAccessLevel() {
		return accessLevel;
	}
	public void setAccessLevel(AccessLevelType accessLevel) {
		this.accessLevel = accessLevel;
	}
	private AccessLevelType accessLevel;

	@ManyToMany
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	public void addUser(User user){
		if(users == null){
			users = new HashSet<>();
		}
		users.add(user);
	}
	private Set <User> users;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || !(o instanceof AccessLevel)) return false;
		if (!super.equals(o)) return false;
		AccessLevel that = (AccessLevel) o;
		return accessLevel == that.accessLevel;

	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (accessLevel != null ? accessLevel.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "AccessLevel{" +
				"accessLevel=" + accessLevel +
				'}';
	}
}
