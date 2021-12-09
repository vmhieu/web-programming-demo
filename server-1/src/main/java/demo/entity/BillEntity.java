package demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "bill")
public class BillEntity extends BaseEntity {

	@Column
	private int month;

	@Column
	private int year;

	@Column(name = "total_price")
	private float totalPrice;

	@ManyToOne
	@JoinColumn(name = "student_id")
	private StudentEntity studentEntity;

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public StudentEntity getStudentEntity() {
		return studentEntity;
	}

	public void setStudentEntity(StudentEntity studentEntity) {
		this.studentEntity = studentEntity;
	}
}
