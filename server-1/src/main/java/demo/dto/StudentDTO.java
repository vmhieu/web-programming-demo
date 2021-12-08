package demo.dto;

import java.util.Date;

public class StudentDTO extends AbstractDTO{

	private String name;
	private String studentCode;
	private String identificationNo;
	private Date birthDate;
	private String grade;
	private String address;
//	private long roomID;
//	private Object roomObject;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStudentCode() {
		return studentCode;
	}
	public void setStudentCode(String studentCode) {
		this.studentCode = studentCode;
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
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
}
