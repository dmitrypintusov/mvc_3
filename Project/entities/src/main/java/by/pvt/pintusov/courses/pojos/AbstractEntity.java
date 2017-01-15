package by.pvt.pintusov.courses.pojos;
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
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Integer id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public int hashCode () {
		final int alfa = 17;
		int result = 1;
		result = alfa * result + id;
		return result;
	}

	@Override
	public boolean equals (Object o) {
		if (this == o) {return true;}
		if (o == null || !(o instanceof AbstractEntity)) {return false;}
		AbstractEntity other = (AbstractEntity) o;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "AbstractEntity {id=" + id + "}";
	}
}
