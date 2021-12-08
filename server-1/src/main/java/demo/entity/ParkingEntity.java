package demo.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ParkingEntity extends ServiceEntity {

	@Column(name = "start_time")
	@CreatedDate
	private Date startTime;

	@Column(name = "end_time")
	@LastModifiedDate
	private Date endTime;

	@Column(name = "is_moving")
	private boolean isMoving;

	@Column(name = "number_plate")
	private String numberPlate;

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public boolean isMoving() {
		return isMoving;
	}

	public void setMoving(boolean moving) {
		isMoving = moving;
	}

	public String getNumberPlate() {
		return numberPlate;
	}

	public void setNumberPlate(String numberPlate) {
		this.numberPlate = numberPlate;
	}
}
