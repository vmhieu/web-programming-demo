package demo.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import demo.dto.GuestDTO;
import demo.entity.GuestEntity;
import demo.entity.ResponseObject;

public interface GuestService {
	ResponseEntity<ResponseObject> create(GuestDTO guest);
	ResponseEntity<ResponseObject> update(GuestDTO guest);
	List<GuestDTO> getAll();
	ResponseEntity<ResponseObject> getById(long id);
//	ResponseEntity<GuestDTO> getName(); 
	void delete(long id);
}
