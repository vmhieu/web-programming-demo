package demo.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.OneToMany;

public class RoomEntity extends BaseEntity{

	@OneToMany(mappedBy = "rooms")
	private List<StudentEntity> students = new ArrayList<>();
}
