package demo.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class StudentEntity extends BaseEntity{
	

	@Column(name = "name")
	private String name;
	
	@Column(name = "studentCode")
	private String studentCode;
	
	@Column(name = "identification_no")
	private String identificationNo;
	
	@Column(name = "birth_date")
	private Date birthDate;
	
	@Column(name = "grade")
	private String grade;
	
	@Column(name = "address")
	private String address;
	
//	@ManyToOne
//	@JoinColumn(name = "room_id")
//	private RoomEntity rooms;
	
//	@OneToMany(mappedBy="students_bill")
//	private List<BillEntity> bill;
	
	@OneToMany(mappedBy="studentguest", cascade=CascadeType.ALL)
	private List<GuestEntity> guest;
	
//	public StudentEntity(String initStudentCode) {
//		////
//	}
	
//	@OneToMany(mappedBy="students_vehicle")
//	private List<VehicleEntity> vehicle;
//	
//	@OneToMany(mappedBy="students_service")
//	private List<ServiceEntity> service;

//	public StudentEntity() {
//		super();
//	}

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

	public List<GuestEntity> getGuest() {
		return guest;
	}

	public void setGuest(List<GuestEntity> guest) {
		this.guest = guest;
	}

//	public RoomEntity getRooms() {
//		return rooms;
//	}
//
//	public void setRooms(RoomEntity rooms) {
//		this.rooms = rooms;
//	}

//	public List<BillEntity> getBill() {
//		return bill;
//	}
//
//	public void setBill(List<BillEntity> bill) {
//		this.bill = bill;
//	}

	

//	public List<VehicleEntity> getVehicle() {
//		return vehicle;
//	}
//
//	public void setVehicle(List<VehicleEntity> vehicle) {
//		this.vehicle = vehicle;
//	}
//
//	public List<ServiceEntity> getService() {
//		return service;
//	}
//
//	public void setService(List<ServiceEntity> service) {
//		this.service = service;
//	}
	
	
}
