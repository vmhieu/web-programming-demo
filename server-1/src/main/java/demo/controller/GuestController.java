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

import demo.dto.GuestDTO;
import demo.entity.GuestEntity;
import demo.entity.ResponseObject;
import demo.service.GuestService;

@CrossOrigin
@RestController
public class GuestController {

	@Autowired
	private GuestService guestService;
	
	@PostMapping(value = "/api/guest")
	public ResponseEntity<ResponseObject> create(@RequestBody GuestDTO model){
		return guestService.create(model);
	}
	
	@PutMapping(value = "/api/guest/{id}")
	public ResponseEntity<ResponseObject> update(@PathVariable("id") long id, @RequestBody GuestDTO model){
		model.setId(id);
		return guestService.update(model);
	}
	
	@GetMapping(value = "/api/guest")
	public List<GuestDTO> findAll(){
		return  guestService.getAll();

	}
	
	@GetMapping(value = "/api/guest/{id}")
	public ResponseEntity<ResponseObject> getById(@PathVariable long id){
		return guestService.getById(id);
	}
	
	@DeleteMapping(value = "/api/guest/{id}")
	public void delete(@RequestBody long id) {
		guestService.delete(id);
	}
}
