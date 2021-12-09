package demo.entity;

import javax.persistence.*;

@Entity
public class LaundryEntity extends ServiceEntity {
	
	@Column
	private float weight;
	
	public float getWeight() {
		return weight;
	}
	
	public void setWeight(float weight) {
		this.weight= weight;
	}
}
