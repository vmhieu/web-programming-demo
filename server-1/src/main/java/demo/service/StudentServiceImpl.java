package demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import demo.dto.RoomDTO;
import demo.dto.StudentDTO;
import demo.dto.StudentDTO;
import demo.entity.StudentEntity;
import demo.repository.RoomRepository;
import demo.repository.StudentRepository;
import demo.entity.ResponseObject;
import demo.entity.RoomEntity;
import demo.entity.StudentEntity;

@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public ResponseEntity<ResponseObject> create(StudentDTO student) {

		StudentEntity studentEntity = new StudentEntity();
		studentEntity = modelMapper.map(student, StudentEntity.class);
		RoomEntity roomEntity = roomRepository.findById(student.getRoomID());
		studentEntity.setRooms(roomEntity);
	//	if(studentEntity.getId() == Student.getStudentID() ) {
			studentEntity = studentRepository.save(studentEntity);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "create successfully", ""));
//		} else {
//			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
//					.body(new ResponseObject("failed", "Insert Student successfully", ""));
//		}
		
	}

	@Override
	public ResponseEntity<ResponseObject> update(StudentDTO student) {

		StudentEntity studentDb = this.studentRepository.findById(student.getId());
		if (studentDb.getId() != 0) {
//			StudentEntity studentUpdate = studentDb.get();
			studentDb = modelMapper.map(student, StudentEntity.class);	
			studentRepository.save(studentDb);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("ok", "Insert Student successfully", student));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
					.body(new ResponseObject("failed", "Insert Student successfully", ""));
		}
	}

	@Override
	public List<StudentDTO> getAll() {
		List<StudentDTO> results = new ArrayList<>();
		List<StudentEntity> entities = studentRepository.findAll();
		List<RoomEntity> roomEntities = roomRepository.findAll();
		for (StudentEntity item : entities) {
			for(RoomEntity roomEntity : roomEntities) {
				StudentDTO studentDTO = modelMapper.map(item, StudentDTO.class);
				if(roomEntity.getId() == studentDTO.getRoomID() ) {
					RoomDTO roomDTO = modelMapper.map(roomEntity, RoomDTO.class);
					studentDTO.setRoomObject(roomDTO);
					results.add(studentDTO);
				}			
			}		
		}
		return results;

	}

	@Override
	public ResponseEntity<ResponseObject> getById(long id) {
		StudentEntity studentEntity = studentRepository.findById(id);
		if (studentEntity.getId() != 0) {

			StudentDTO StudentDTO = modelMapper.map(studentEntity, StudentDTO.class);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("ok", "Get successfully", StudentDTO));
		} else {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("failed", " successfully", ""));
		}
	}

	@Override
	public void delete(long id) {
		StudentEntity StudentEntity = studentRepository.findById(id);
		if (StudentEntity.getId() != 0) {
			studentRepository.deleteById(id);
		//	StudentDTO StudentDTO = modelMapper.map(StudentEntity, StudentDTO.class);
			

	}

}
}
