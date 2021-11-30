package demo.entity;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name = "guest")
public class GuestEntity extends BaseEntity{

	@Column(name = "name")
	private String name;
	
	@Column(name = "identification_no")
	private String identificationNo;
	
	@Column(name = "birth_date")
	private Date birthDate;
	
	
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "student_id")
	private StudentEntity studentguest;
	
	@Column
	@CreationTimestamp
	private Date date;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdentificationNo() {
		return identificationNo;
	}

	public void setIdentificationNo(String identificationNo) {
		this.identificationNo = identificationNo;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public StudentEntity getStudentguest() {
		return studentguest;
	}

	public void setStudentguest(StudentEntity studentguest) {
		this.studentguest = studentguest;
	}

	
	
	
}
