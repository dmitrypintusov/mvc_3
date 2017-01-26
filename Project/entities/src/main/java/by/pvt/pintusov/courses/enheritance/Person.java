package by.pvt.pintusov.courses.enheritance;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Project name: courses
 * Created by Дмитрий
 * Date: 23.01.2017.
 */

@Data
@NoArgsConstructor
@Entity
@Table (name = "PERSON")
//@Inheritance (strategy = InheritanceType.SINGLE_TABLE)
@Inheritance (strategy = InheritanceType.JOINED)
//@DiscriminatorColumn (name = "PERSON_TYPE", discriminatorType = DiscriminatorType.CHAR)
//@DiscriminatorValue("P")
public class Person implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Integer id;
	@Column
	private String name;
	@Column
	private String surname;
}
