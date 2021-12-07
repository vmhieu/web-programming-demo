package demo.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "vehicle")
public class VehicleEntity extends BaseEntity {

	@Column(name = "number_plate")
	private String numberPlate;

	@Column(name = "has_ticket")
	private boolean hasTicket;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "student_id")
	private StudentEntity studentEntity;

	public String getNumberPlate() {
		return numberPlate;
	}

	public void setNumberPlate(String numberPlate) {
		this.numberPlate = numberPlate;
	}

	public StudentEntity getStudent_vehicle() {
		return studentEntity;
	}

	public void setStudent_vehicle(StudentEntity student_vehicle) {
		this.studentEntity = student_vehicle;
	}

	public boolean isHasTicket() {
		return hasTicket;
	}

	public void setHasTicket(boolean hasTicket) {
		this.hasTicket = hasTicket;
	}

}
