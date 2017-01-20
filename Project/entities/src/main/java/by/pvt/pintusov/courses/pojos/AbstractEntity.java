package by.pvt.pintusov.courses.pojos;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Describes the abstract entity <strong>AbstractEntity</strong>
 * @author pintusov
 * @version 1.1
 */

@MappedSuperclass
@Access(AccessType.PROPERTY)
public abstract class AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	public AbstractEntity() {}

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	protected Integer id;

	@Override
	public boolean equals (Object o) {
		if (this == o) {return true;}
		if (o == null || !(o instanceof AbstractEntity)) {return false;}
		AbstractEntity abstractEntity = (AbstractEntity) o;
		return id == abstractEntity.id;
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "AbstractEntity {id=" + id + "}";
	}
}
