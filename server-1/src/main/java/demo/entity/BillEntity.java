package demo.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class BillEntity extends BaseEntity{

	@Column(name = "totalPrice")
	private float total;
	
	@Column(name = "month")
	private String month;
	
	@Column(name = "year")
	private Date year;

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Date getYear() {
		return year;
	}

	public void setYear(Date year) {
		this.year = year;
	}
	
	
}
