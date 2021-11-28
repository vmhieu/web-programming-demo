package demo.entity;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class GuestEntity extends BaseEntity{

	@ManyToOne()
	@JoinColumn(name = "student_id")
	private StudentEntity student_guest;
}
