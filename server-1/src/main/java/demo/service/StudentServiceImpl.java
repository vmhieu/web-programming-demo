package demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.query.criteria.internal.expression.function.AggregationFunction.COUNT;
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
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ResponseEntity<?> create(StudentDTO student) {

		try {
			boolean hasStudentCode = studentRepository.existsByStudentCode(student.getStudentCode());
			boolean hasIdentificationNo = studentRepository.existsByIdentificationNo(student.getIdentificationNo());
			if(hasStudentCode) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body(new ResponseObject("failed", "Mã sinh viên đã tồn tại"));
			} else if(hasIdentificationNo){
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body(new ResponseObject("failed", "Số chứng minh thư đã tồn tại"));
			} else{
				StudentEntity studentEntity = modelMapper.map(student, StudentEntity.class);
				Optional<RoomEntity> roomEntity = roomRepository.findById(student.getRoomID());
				if (!roomEntity.isPresent()) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST)
							.body(new ResponseObject("failed", "Phòng không tồn tại"));
				}
				List<StudentEntity> listStudentEntities = studentRepository.findAll();
				RoomEntity roomEntity2 = roomRepository.findById(student.getRoomID()).get();
				int count = 0;
				for (StudentEntity item : listStudentEntities) {
					if (item.getRoom().getId() == student.getRoomID()) {
						count++;
					}
				}
					if (count >= roomEntity2.getMaximum()) {
						return ResponseEntity.status(HttpStatus.BAD_REQUEST)
								.body(new ResponseObject("failed", "Số người trong phòng đã tối đa"));
					} else {
						studentEntity.setRoom(roomEntity.get());
						studentEntity = studentRepository.save(studentEntity);
						return ResponseEntity.status(HttpStatus.OK)
								.body(new ResponseObject("ok", "Tạo mới sinh viên thành công"));
					}
			}
			
		} catch (Exception e) {
			System.out.println(e.toString());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject("failed", "Internal Server Error!!! " + e.toString()));
		}

	}

	@Override
	public ResponseEntity<?> update(StudentDTO student) {
		Optional<StudentEntity> studentEntity = this.studentRepository.findById(student.getId());
		if (!studentEntity.isPresent()) {	
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
					.body(new ResponseObject("failed", "Không tìm thấy sinh viên"));
			} else{
			StudentEntity studentUpdate = studentEntity.get();
			studentUpdate = modelMapper.map(student, StudentEntity.class);
			studentRepository.save(studentUpdate);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("ok", "Cập nhật sinh viên thành công", student));
		
		}
	}

	@Override
	public List<StudentDTO> getAll() {
		List<StudentDTO> results = new ArrayList<>();
		List<StudentEntity> entities = studentRepository.findAll();
		for (StudentEntity item : entities) {
			RoomEntity roomEntity = item.getRoom();
			StudentDTO studentDTO = modelMapper.map(item, StudentDTO.class);
			RoomDTO roomDTO = modelMapper.map(roomEntity, RoomDTO.class);
			studentDTO.setRoomObject1(roomDTO);
			results.add(studentDTO);
		}
		return results;

	}

	@Override
	public ResponseEntity<?> getById(long id) {
		Optional<StudentEntity> studentEntity = studentRepository.findById(id);
		if (studentEntity.isPresent()) {
			StudentEntity studentEntity2 = studentEntity.get();
//			RoomEntity roomEntity = studentEntity2.getRoom();
			StudentDTO studentDTO = modelMapper.map(studentEntity2, StudentDTO.class);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "Thành công", studentDTO));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
					.body(new ResponseObject("failed", "Không tìm thấy sinh viên"));
		}
	}

	@Override
	public ResponseEntity<?> delete(long id) {
		Optional<StudentEntity> studentEntity = studentRepository.findById(id);
		if (studentEntity.isPresent()) {
			studentRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "Xoá sinh viên thành công"));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
					.body(new ResponseObject("failed", "Xoá sinh viên thất bại"));
		}

	}

	@Override
	public ResponseEntity<?> findByName(String name) {
		List<StudentDTO> results = new ArrayList<>();
		List<StudentEntity> studentEntities = studentRepository.findByNameIgnoreCaseContaining(name);
		if(!studentEntities.isEmpty()) {
			for (StudentEntity item : studentEntities) {
				RoomEntity roomEntity = item.getRoom();
				StudentDTO studentDTO = modelMapper.map(item, StudentDTO.class);
				RoomDTO roomDTO = modelMapper.map(roomEntity, RoomDTO.class);
				studentDTO.setRoomObject1(roomDTO);
				results.add(studentDTO);
			}
			return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "Thành công", results));
		} else {
			return  ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ResponseObject("failed", "Không tìm thấy tên sinh viên"));
		}
	
	}
}
