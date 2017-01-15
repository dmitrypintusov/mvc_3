package by.pvt.pintusov.courses.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Describes the entity <strong>User</strong>
 * @author pintusov
 * @version 1.1
 */

@Entity
@Table
public class User extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	@Column
	private String firstName;
	public String getFirstName () { return firstName; }
	public void setFirstName (String firstName) { this.firstName = firstName; }

	@Column
	private String lastName;
	public String getLastName () { return lastName; }
	public void setLastName (String lastName) { this.lastName = lastName; }

	@Column
	private String login;
	public String getLogin () { return login; }
	public void setLogin (String login) { this.login = login; }

	@Column
	private String password;
	public String getPassword () { return password; }
	public void setPassword (String password) { this.password = password; }

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
