package demo.entity;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

public class VehicleEntity extends BaseEntity{

	@ManyToOne()
	@JoinColumn(name = "student_id")
	private StudentEntity student_vehicle;
	
	@OneToOne
    @JoinColumn(name = "parking_id")
	private ParkingEntity parkings;
}
