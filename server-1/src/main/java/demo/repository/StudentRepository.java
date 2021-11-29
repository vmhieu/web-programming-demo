package demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.entity.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity, Long>{
//	List<StudentEntity> findByStudentCode(String studentCode);
//	StudentEntity findById(long id);
//	StudentEntity findByStudentCode(String studentCode);
	
}
