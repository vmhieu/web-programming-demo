package demo.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.*;

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
	
	@ManyToOne
	@JoinColumn(name = "room_id")
	private RoomEntity room;
	
	@OneToMany(mappedBy="studentEntity")
	private List<BillEntity> bill;
	
	@OneToMany(mappedBy="studentID", cascade=CascadeType.ALL) 
	private List<GuestEntity> guest;
	
	@OneToOne(mappedBy = "studentEntity", cascade = CascadeType.ALL)
	private VehicleEntity vehicleEntity;

	@OneToMany(mappedBy = "studentEntity", cascade = CascadeType.ALL)
	private List<ServiceEntity> serviceEntities;

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



//	public List<BillEntity> getBill() {
//		return bill;
//	}
//
//	public void setBill(List<BillEntity> bill) {
//		this.bill = bill;
//	}

	

//	public List<VehicleEntity> getVehicleEntity() {
//		return vehicle;
//	}
//
//	public void setVehicleEntity(List<VehicleEntity> vehicle) {
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


	public RoomEntity getRoom() {
		return room;
	}

	public void setRoom(RoomEntity room) {
		this.room = room;
	}

	public VehicleEntity getVehicleEntity() {
		return vehicleEntity;
	}

	public void setVehicleEntity(VehicleEntity vehicleEntity) {
		this.vehicleEntity = vehicleEntity;
	}

	public List<ServiceEntity> getServiceEntities() {
		return serviceEntities;
	}

	public void setServiceEntities(List<ServiceEntity> serviceEntities) {
		this.serviceEntities = serviceEntities;
	}
}
