package demo.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "room")
public class RoomEntity extends BaseEntity{
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "price")
	private float priceUnit;
	
	@Column(name = "total")
	private int total;
	
	@Column(name = "maximum")
	private int maximum;
	
	//OneToOne : chỉ ra ở đây là 1 user chỉ có 1 address
	// 1-1 : khóa ngoại bên nào cũng được
	
	//1-n : khóa chính bên n làm khóa ngoại bên 1
	
	//n- n:
	
	//OnetoMany : mappedBy : map với bảng bên đặt khóa ngoại
	
	
	
	//ManytoOne:  @JoinColumn : đặt bên được đặt khóa ngoại để chỉ ra tên bảng , và tạo sự liên kết ngược lại với bên nhiều
				// referencedColumnName : tham chiếu với bảng id bên kia
	
	
		
	
	@OneToMany(mappedBy = "room" ,cascade = CascadeType.MERGE)
	private List<StudentEntity> studentRoom;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public float getPriceUnit() {
		return priceUnit;
	}

	public void setPriceUnit(float priceUnit) {
		this.priceUnit = priceUnit;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMaximum() {
		return maximum;
	}

	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}

	public List<StudentEntity> getStudentRoom() {
		return studentRoom;
	}

	public void setStudentRoom(List<StudentEntity> studentRoom) {
		this.studentRoom = studentRoom;
	}
	

	
}
