package by.pvt.pintusov.courses.enheritance;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Project name: courses
 * Created by Дмитрий
 * Date: 23.01.2017.
 */
@Data
@NoArgsConstructor
@Entity
@Table (name = "TEACHER")
@PrimaryKeyJoinColumn(name = "PERSON_ID")
//@DiscriminatorValue("T")
public class Teacher extends Person {
	private static final long serialVersionUID = 2L;

	@Column
	private String position;
	@Column
	private Long salary;
}
