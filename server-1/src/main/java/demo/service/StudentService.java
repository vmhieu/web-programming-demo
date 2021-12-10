package demo.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import demo.dto.StudentDTO;
import demo.entity.ResponseObject;

public interface StudentService {

	ResponseEntity<?> create(StudentDTO Student);
	ResponseEntity<?> update(StudentDTO Student);
	List<StudentDTO> getAll();
	ResponseEntity<?> findByName(String name);
	ResponseEntity<?> getById(long id);
	ResponseEntity<?> delete(long id);

}
