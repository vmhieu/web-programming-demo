package demo.entity;

import javax.persistence.*;

@Entity
public class FoodEntity extends ServiceEntity {
	
	@Column
	private int times;
	
	public int getTimes() {
		return times;
	}
	
	public void setTimes(int times) {
		this.times= times;
	}
}
