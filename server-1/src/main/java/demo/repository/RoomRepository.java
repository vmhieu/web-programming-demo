package demo.repository;

import demo.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import demo.entity.RoomEntity;

public interface RoomRepository extends JpaRepository<RoomEntity, Long>{
	RoomEntity findByStudentRoom(StudentEntity studentEntity);
	RoomEntity findByNameStartingWith(String name);
	boolean existsByName(String nameRoom);
}
