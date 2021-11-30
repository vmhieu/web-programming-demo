package demo.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import demo.dto.GuestDTO;
import demo.entity.GuestEntity;
import demo.entity.ResponseObject;

public interface GuestService {
	ResponseEntity<ResponseObject> create(GuestDTO guest);
//	GuestEntity create(GuestEntity guest);
	ResponseEntity<ResponseObject> update(GuestEntity guest);
	List<GuestEntity> getAll();
	GuestEntity getById(long id);
	void delete(long id);
}
