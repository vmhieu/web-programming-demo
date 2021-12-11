package demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.entity.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity, Long>{
	List<StudentEntity> findByNameIgnoreCaseContaining(String name);
	//StudentEntity findByName(String name);
	StudentEntity findByStudentCode(String studentCode);
	boolean existsByStudentCode(String studentCode);
	boolean existsByIdentificationNo(String identificationNo);
}
