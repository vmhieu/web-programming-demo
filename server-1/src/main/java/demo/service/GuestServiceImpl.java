package demo.service;

import java.util.ArrayList;
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
	public ResponseEntity<?> create(GuestDTO guest) {

		try {
			GuestEntity guestEntity = modelMapper.map(guest, GuestEntity.class);
			Optional<StudentEntity> studentEntity = studentRepository.findById(guest.getStudentID());
//			System.out.println(guest.getStudentID());
			if (!studentEntity.isPresent()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body(new ResponseObject("fail", "Sinh viên không tồn tại"));
			} else {
				guestEntity.setStudentID(studentEntity.get());
				guestRepository.save(guestEntity);
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "Tạo mới khách thành công"));
			}

		} catch (Exception e) {
			System.out.println(e.toString());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseObject("fail", "Internal Server Error!!! " + e.toString()));
		}

	}

	@Override
	public ResponseEntity<?> update(GuestDTO guest) {

		Optional<GuestEntity> guestDb = this.guestRepository.findById(guest.getId());
		Optional<StudentEntity> studentEntity = studentRepository.findById(guest.getStudentID());
		if (!guestDb.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
					.body(new ResponseObject("failed", "Cập nhật thất bại"));
		} else if(!studentEntity.isPresent()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ResponseObject("fail", "Sinh viên không tồn tại"));
		} else {
			
			GuestEntity guestUpdate = guestDb.get();
			guestUpdate = modelMapper.map(guest, GuestEntity.class);
			guestUpdate.setCreatedDate(guestDb.get().getCreatedDate());
			guestRepository.save(guestUpdate);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "Cập nhật thành công", guest));		
		}
	}

	@Override
	public List<GuestDTO> getAll() {
		List<GuestDTO> results = new ArrayList<>();
		List<GuestEntity> entities = guestRepository.findAll();

		for (GuestEntity item : entities) {
			StudentEntity studentEntity = item.getStudentID();
			GuestDTO guestDTO = modelMapper.map(item, GuestDTO.class);
			StudentDTO studentDTO = modelMapper.map(studentEntity, StudentDTO.class);
			guestDTO.setStudentObject(studentDTO);
			results.add(guestDTO);
		}
		return results;

	}

	@Override
	public ResponseEntity<?> getById(long id) {
		Optional<GuestEntity> guestEntity = guestRepository.findById(id);
		if (guestEntity.isPresent()) {
			GuestEntity guestEntity2 = guestEntity.get();	
			StudentEntity studentEntity = guestEntity2.getStudentID();
			GuestDTO guestDTO = modelMapper.map(guestEntity2, GuestDTO.class);
			StudentDTO studentDTO = modelMapper.map(studentEntity, StudentDTO.class);
			guestDTO.setStudentObject(studentDTO);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "Thành công", guestDTO));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new ResponseObject("failed", "Không tìm thấy khách"));
		}
	}

	@Override
	public ResponseEntity<?> delete(long id) {
		Optional<GuestEntity> guestEntity = guestRepository.findById(id);
		if (guestEntity.isPresent()) {
			guestEntity.get();
			guestRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "Xoá khách thành công"));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
					.body(new ResponseObject("failed", "Xoá khách thất bại"));
		}

	}

}