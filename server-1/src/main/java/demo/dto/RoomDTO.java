package demo.dto;

import java.util.List;

public class RoomDTO extends AbstractDTO{

	private String type;
	private float priceUnit;
	private int total;
	private int maximum;
//	private long studentID;
//	private Object studentObject;
	private List<StudentDTO> listStudent;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public float getPriceUnit() {
		return priceUnit;
	}
	public void setPriceUnit(float priceUnit) {
		this.priceUnit = priceUnit;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getMaximum() {
		return maximum;
	}
	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}
//	public Object getStudentObject() {
//		return studentObject;
//	}
//	public void setStudentObject(Object studentObject) {
//		this.studentObject = studentObject;
//	}
	public List<StudentDTO> getListStudent() {
		return listStudent;
	}
	public void setListStudent(List<StudentDTO> listStudent) {
		this.listStudent = listStudent;
	}


	
}
