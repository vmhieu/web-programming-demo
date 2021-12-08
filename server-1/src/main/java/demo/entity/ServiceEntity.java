package demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "service")
public class ServiceEntity extends BaseEntity{

	@Column(name = "service_code")
	private String serviceCode;

	@Column
	private String name;

	@Column(name = "price_unit")
	private float priceUnit;

	@Column
	private float price;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "student_id")
	private StudentEntity studentEntity;

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPriceUnit() {
		return priceUnit;
	}

	public void setPriceUnit(float priceUnit) {
		this.priceUnit = priceUnit;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public StudentEntity getStudentEntity() {
		return studentEntity;
	}

	public void setStudentEntity(StudentEntity studentEntity) {
		this.studentEntity = studentEntity;
	}
}
