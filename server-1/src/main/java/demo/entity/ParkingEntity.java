package demo.entity;

import javax.persistence.OneToOne;

public class ParkingEntity {

	@OneToOne(mappedBy = "parkings")
	private VehicleEntity vehicle;
}
