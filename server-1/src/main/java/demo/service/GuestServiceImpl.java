package demo.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import demo.dto.GuestDTO;
import demo.dto.StudentDTO;
import demo.entity.GuestEntity;
import demo.entity.ResponseObject;
import demo.entity.StudentEntity;
import demo.repository.GuestRepository;
import demo.repository.StudentRepository;

@Service
public class GuestServiceImpl implements GuestService {

	@Autowired
	private GuestRepository guestRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ResponseEntity<ResponseObject> create(GuestDTO guest) {

		GuestEntity guestEntity = new GuestEntity();
		guestEntity = modelMapper.map(guest, GuestEntity.class);
	//	StudentEntity studentEntity = studentRepository.findById(guest.getStudentID());
	//	System.out.println(studentEntity.getId());
		StudentEntity studentEntity = studentRepository.findById(guest.getStudentID());
	//	System.out.println(studentRepository.findById(studentEntity.getId()));
	//	studentRepository.findAll(studentEntity.getId()).forEach()
		guestEntity.setStudentID(studentEntity);
		if(studentEntity.getId() == guest.getStudentID() ) {
			guestEntity = guestRepository.save(guestEntity);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "create successfully", ""));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
					.body(new ResponseObject("failed", "Insert Guest successfully", ""));
		}
		


	}

	@Override
	public ResponseEntity<ResponseObject> update(GuestDTO guest) {

		Optional<GuestEntity> guestDb = this.guestRepository.findById(guest.getId());
		if (guestDb.isPresent()) {
			GuestEntity guestUpdate = guestDb.get();
			guestUpdate = modelMapper.map(guest, GuestEntity.class);

			guestRepository.save(guestUpdate);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("ok", "Insert Guest successfully", guest));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
					.body(new ResponseObject("failed", "Insert Guest successfully", ""));
		}
	}

	@Override
	public List<GuestDTO> getAll() {
		List<GuestDTO> results = new ArrayList<>();
		List<GuestEntity> entities = guestRepository.findAll();
		List<StudentEntity> studentEntities = studentRepository.findAll();
		for (GuestEntity item : entities) {
			for(StudentEntity student : studentEntities) {
				GuestDTO guestDTO = modelMapper.map(item, GuestDTO.class);
				if(student.getId() == guestDTO.getStudentID() ) {
					StudentDTO studentDTO = modelMapper.map(student, StudentDTO.class);
					guestDTO.setStudentObject(studentDTO);
					results.add(guestDTO);
				}
					
			}
			
		}
		return results;

	}

	@Override
	public ResponseEntity<ResponseObject> getById(long id) {
		GuestEntity guestEntity = guestRepository.findById(id).get();
		if (guestEntity.getId() != 0) {

			GuestDTO guestDTO = modelMapper.map(guestEntity, GuestDTO.class);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("ok", "Get successfully", guestDTO));
		} else {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("failed", " successfully", ""));
		}
	}

	@Override
	public void delete(long id) {
		GuestEntity guestEntity = guestRepository.findById(id).get();
		if (guestEntity.getId() != 0) {
			guestRepository.deleteById(id);
			GuestDTO guestDTO = modelMapper.map(guestEntity, GuestDTO.class);


		}

	}
}
