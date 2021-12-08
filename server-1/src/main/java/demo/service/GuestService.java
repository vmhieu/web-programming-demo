package demo.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import demo.dto.GuestDTO;
import demo.entity.GuestEntity;
import demo.entity.ResponseObject;

public interface GuestService {
	ResponseEntity<?> create(GuestDTO guest);
	ResponseEntity<?> update(GuestDTO guest);
	List<GuestDTO> getAll();
	ResponseEntity<?> getById(long id);
//	ResponseEntity<GuestDTO> getName(); 
//	void delete(long id);
	ResponseEntity<?> delete(long id);
}
