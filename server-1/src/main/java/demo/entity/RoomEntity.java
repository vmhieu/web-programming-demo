package demo.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "room")
public class RoomEntity extends BaseEntity{

	
	@OneToMany(mappedBy = "rooms")
	private List<StudentEntity> studentRoom;

	public List<StudentEntity> getStudentRoom() {
		return studentRoom;
	}

	public void setStudentRoom(List<StudentEntity> studentRoom) {
		this.studentRoom = studentRoom;
	}
	
	
}
