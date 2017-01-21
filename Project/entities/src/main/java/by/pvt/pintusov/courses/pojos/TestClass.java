package by.pvt.pintusov.courses.pojos;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;

/**
 * Project name: courses
 * Created by Дмитрий
 * Date: 20.01.2017.
 */

@Immutable
@Entity
@Table
public class TestClass extends AbstractEntity {

	@Access(AccessType.FIELD)
	@Column (insertable = true, updatable = false)
	private String beginDate;
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	@Access(AccessType.FIELD)
	@Column (insertable = false, updatable = true)
	private String endDate;
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	@Access(AccessType.PROPERTY)
	public Integer getTax() {
		return tax;
	}
	public void setTax(Integer tax) {
		this.tax = tax;
	}

	private Integer tax = 20;
}
