package demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.entity.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity, Long>{

	StudentEntity findById(long id);
	StudentEntity findByName(String name);
	StudentEntity findByStudentCode(String studentCode);

	
}
