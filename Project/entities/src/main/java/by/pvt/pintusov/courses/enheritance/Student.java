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
@Table (name = "STUDENT")
@PrimaryKeyJoinColumn (name = "PERSON_ID")
//@DiscriminatorValue("S")
public class Student extends Person {
	private static final long serialVersionUID = 3L;

	@Column
	private String faculty;
	@Column
	private Double mark;
}
