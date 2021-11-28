package demo.entity;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class BillEntity extends BaseEntity{

	@ManyToOne()
	@JoinColumn(name = "bill_id")
	private StudentEntity students_bill;
}
