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

	@OneToOne
	@JoinColumn(name = "student_id")
	private StudentEntity studentEntity;

	public String getNumberPlate() {
		return numberPlate;
	}

	public void setNumberPlate(String numberPlate) {
		this.numberPlate = numberPlate;
	}

	public boolean isHasTicket() {
		return hasTicket;
	}

	public void setHasTicket(boolean hasTicket) {
		this.hasTicket = hasTicket;
	}

	public StudentEntity getStudentEntity() {
		return studentEntity;
	}

	public void setStudentEntity(StudentEntity studentEntity) {
		this.studentEntity = studentEntity;
	}
}
