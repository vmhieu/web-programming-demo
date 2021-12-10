package demo.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


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
		
	@Column
	@CreatedDate
	private Date createdDate;
	
	@ManyToOne
	@JoinColumn(name = "student_id")
	private StudentEntity studentID;
	
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

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public StudentEntity getStudentID() {
		return studentID;
	}

	public void setStudentID(StudentEntity studentID) {
		this.studentID = studentID;
	}

	
}
