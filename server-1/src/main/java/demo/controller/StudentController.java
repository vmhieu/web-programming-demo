package demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import demo.dto.StudentDTO;
import demo.entity.ResponseObject;
import demo.service.StudentService;

@CrossOrigin
@RestController
public class StudentController {

	@Autowired
	private StudentService StudentService;
	
	@PostMapping(value = "/api/student")
	public ResponseEntity<ResponseObject> create(@RequestBody StudentDTO model){
		return StudentService.create(model);
	}
	
	@PutMapping(value = "/api/student/{id}")
	public ResponseEntity<ResponseObject> update(@PathVariable("id") long id, @RequestBody StudentDTO model){
		model.setId(id);
		return StudentService.update(model);
	}
	
	@GetMapping(value = "/api/student")
	public List<StudentDTO> findAll(){
		return  StudentService.getAll();

	}
	
	@GetMapping(value = "/api/student/{id}")
	public ResponseEntity<ResponseObject> getById(@PathVariable long id){
		return StudentService.getById(id);
	}
	
	@DeleteMapping(value = "/api/student/{id}")
	public void delete(@RequestBody long id) {
		StudentService.delete(id);
	}
}
