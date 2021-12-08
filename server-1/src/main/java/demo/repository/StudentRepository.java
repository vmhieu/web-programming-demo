package demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.entity.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity, Long>{

	StudentEntity findByName(String name);
	StudentEntity findByStudentCode(String studentCode);

}
