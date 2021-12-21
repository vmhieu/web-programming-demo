package demo.service.room;

import java.util.List;

import org.springframework.http.ResponseEntity;

import demo.dto.RoomDTO;
import demo.entity.ResponseObject;

public interface RoomService {
	
	List<RoomDTO> getAll();
	ResponseEntity<ResponseObject> create(RoomDTO roomDTO);
	ResponseEntity<ResponseObject> findById(long id);
	ResponseEntity<ResponseObject> update(RoomDTO roomDTO);
	ResponseEntity<ResponseObject> deleteById(long id);
	ResponseEntity<?> findByName(String name);
	ResponseEntity<?> getBillRoom();
}
