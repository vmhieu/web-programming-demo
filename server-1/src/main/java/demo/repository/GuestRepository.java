package demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import demo.entity.GuestEntity;
import demo.entity.StudentEntity;

public interface GuestRepository extends JpaRepository<GuestEntity, Long>{
	GuestEntity  findByStudentID(StudentEntity studentID);
}
