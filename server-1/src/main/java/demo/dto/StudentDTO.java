package demo.dto;

import java.util.Date;

public class StudentDTO extends AbstractDTO{

	private String name;
	private String studentCode;
	private String identificationNo;
	private Date birthDate;
	private String grade;
	private String address;
	private long roomID;
	private Object roomObject1;
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
	public long getRoomID() {
		return roomID;
	}
	public void setRoomID(long roomID) {
		this.roomID = roomID;
	}
//	public Object getRoomObject() {
//		return roomObject;
//	}
//	public void setRoomObject(Object roomObject) {
//		this.roomObject = roomObject;
//	}
	public Object getRoomObject1() {
		return roomObject1;
	}
	public void setRoomObject1(Object roomObject1) {
		this.roomObject1 = roomObject1;
	}
	
}
