package demo.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import demo.dto.StudentDTO;
import demo.entity.ResponseObject;

public interface StudentService {

	ResponseEntity<ResponseObject> create(StudentDTO Student);
	ResponseEntity<ResponseObject> update(StudentDTO Student);
	List<StudentDTO> getAll();
	ResponseEntity<ResponseObject> getById(long id);
	void delete(long id);
}
