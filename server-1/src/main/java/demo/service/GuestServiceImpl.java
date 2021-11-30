package demo.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import demo.dto.GuestDTO;
import demo.entity.GuestEntity;
import demo.entity.ResponseObject;
import demo.entity.StudentEntity;
import demo.repository.GuestRepository;
import demo.repository.StudentRepository;

@Service
public class GuestServiceImpl implements GuestService{

	@Autowired
	private GuestRepository guestRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public ResponseEntity<ResponseObject> create(GuestDTO guest) {
		
		GuestEntity guestEntity = new GuestEntity();
		StudentEntity studentEntity = studentRepository.findById(guest.getStudentID());
			guestEntity.setStudentguest(studentEntity);
			guestEntity = guestRepository.save(guestEntity);			
			guest = modelMapper.map(studentEntity, GuestDTO.class);
			return ResponseEntity.status(HttpStatus.OK).body(
					new ResponseObject("ok", "create successfully", guest));
//		} else {
//			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
//					new ResponseObject("failed", "can not find StudentCode", ""));
//		}
			
	}

	@Override
	public ResponseEntity<ResponseObject> update(GuestEntity guest) {
		Optional<GuestEntity> guestDb = this.guestRepository.findById(guest.getId());
		if(guestDb.isPresent()) {
			GuestEntity guestUpdate = guestDb.get();
			guestUpdate.setId(guest.getId());
			guestUpdate.setName(guest.getName());
			guestUpdate.setBirthDate(guest.getBirthDate());
			guestUpdate.setIdentificationNo(guest.getIdentificationNo());
			guestUpdate.setDate(guest.getDate());
			guestUpdate.setStudentguest(guest.getStudentguest());
			guestRepository.save(guestUpdate);
			return ResponseEntity.status(HttpStatus.OK).body(
					new ResponseObject("ok", "Insert Guest successfully", guestUpdate));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
					new ResponseObject("failed", "Insert Guest successfully", ""));
		}
	}

	@Override
	public List<GuestEntity> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GuestEntity getById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}
	


}
