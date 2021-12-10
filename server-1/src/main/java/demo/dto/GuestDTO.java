package demo.dto;

import java.sql.Date;

public class GuestDTO extends AbstractDTO {

	private String name;
	private String identificationNo;
	private Date birthDate;
	private Date createdDate;
	private long studentID;
	private Object studentObject;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public long getStudentID() {
		return studentID;
	}

	public void setStudentID(long studentID) {
		this.studentID = studentID;
	}

	public String getIdentificationNo() {
		return identificationNo;
	}

	public void setIdentificationNo(String identificationNo) {
		this.identificationNo = identificationNo;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Object getStudentObject() {
		return studentObject;
	}

	public void setStudentObject(Object studentObject) {
		this.studentObject = studentObject;
	}
	
	

}
