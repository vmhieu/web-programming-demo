package demo.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import demo.entity.GuestEntity;
import demo.entity.ResponseObject;

public interface GuestService {
	ResponseEntity<ResponseObject> create(GuestEntity guest);
//	GuestEntity create(GuestEntity guest);
	ResponseEntity<ResponseObject> update(GuestEntity guest);
	List<GuestEntity> getAll();
	GuestEntity getById(long id);
	void delete(long id);
}
