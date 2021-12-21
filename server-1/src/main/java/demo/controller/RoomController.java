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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import demo.dto.RoomDTO;
import demo.entity.ResponseObject;
import demo.service.room.RoomService;
@CrossOrigin
@RestController

public class RoomController {

	@Autowired
	private RoomService roomService;
	
	@GetMapping("/api/room/")
	public ResponseEntity<?> findByName(@RequestParam(name="name") String nameRoom){
		return roomService.findByName(nameRoom);
	}
	
	@GetMapping("/api/room")
	public List<RoomDTO> getAll(){
		return roomService.getAll();
	}
	
	@PostMapping("/api/room")
	public ResponseEntity<ResponseObject> create(@RequestBody RoomDTO roomDTO){
		return roomService.create(roomDTO);
	}
	
	@GetMapping("/api/room/{id}")
	public ResponseEntity<ResponseObject> findById(@PathVariable long id){
		return roomService.findById(id);
	}
	
	
	@PutMapping("/api/room")
	public ResponseEntity<ResponseObject> update(@RequestBody RoomDTO roomDTO){
		return roomService.update(roomDTO);
	}
	
	@DeleteMapping("/api/room/{id}")
	public ResponseEntity<ResponseObject> deleteById(@PathVariable long id){
		return roomService.deleteById(id);
	}
	
	@GetMapping("/api/room/bill")
	public ResponseEntity<?> getRoomBill(){
		return roomService.getBillRoom();
	}
	
}
