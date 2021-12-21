package demo.service;

import demo.dto.LaundryDTO;
import demo.dto.StudentDTO;
import demo.entity.FoodEntity;
import demo.entity.LaundryEntity;
import demo.entity.ResponseObject;
import demo.entity.StudentEntity;
import demo.repository.StudentRepository;
import demo.repository.LaundryRepository;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LaundryServiceImpl implements LaundryService {
	
	@Autowired
    private StudentRepository studentRepository;
	
	@Autowired
	private LaundryRepository laundryRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public ResponseEntity<?> create(LaundryDTO dto) {
			LaundryEntity laundryEntity = modelMapper.map(dto, LaundryEntity.class);
			Optional<StudentEntity> studentEntity = studentRepository.findById(dto.getStudentId());
			System.out.println(dto.getStudentId());
			if (!studentEntity.isPresent()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject("fail", "Sinh viên không tồn tại"));
			} else {
				laundryEntity.setStudentEntity(studentEntity.get());
				laundryEntity.setWeight(dto.getWeight());
				laundryEntity.setPrice(dto.getPriceUnit() * dto.getWeight());
				laundryRepository.save(laundryEntity);
			}
			return ResponseEntity.ok(new ResponseObject("ok", "Thêm lần giặt là thành công", ""));
	}
	
	@Override
	public ResponseEntity<?> update(LaundryDTO dto) {
			Optional<LaundryEntity> laundryEntity = laundryRepository.findById(dto.getId());
			Optional<StudentEntity> studentEntity = studentRepository.findById(dto.getStudentId());
			if (laundryEntity.isPresent()) {
				LaundryEntity laundryEntityUpdate = laundryEntity.get();
				if (dto.getServiceCode() != null) {
					laundryEntityUpdate.setServiceCode(dto.getServiceCode());
                }
                if (dto.getName() != null) {
                	laundryEntityUpdate.setName(dto.getName());
                }
                laundryEntityUpdate.setPrice(dto.getPrice());
                laundryEntityUpdate.setWeight(dto.getWeight());
				laundryEntityUpdate.setPrice(dto.getPriceUnit() * dto.getWeight());
//                }
				LaundryEntity laundryResponse = laundryRepository.save(laundryEntityUpdate);
                LaundryDTO responseDto = modelMapper.map(laundryResponse, LaundryDTO.class);
                StudentDTO studentDTO = modelMapper.map(laundryResponse.getStudentEntity(), StudentDTO.class);
                responseDto.setStudentObject(studentDTO);
				return ResponseEntity.ok(new ResponseObject("ok", "Cập nhật lượt giặt là thành công", ""));
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseObject("fail", "Cập nhật lượt giặt là không thành công"));
	}
	
	@Override
	public ResponseEntity<?> getAll() {
		try {
			List<LaundryDTO> result = new ArrayList<>();
			List<LaundryEntity> laundryEntities = laundryRepository.findAll();
			for (LaundryEntity laundryEntity : laundryEntities) {
				StudentEntity studentEntity = laundryEntity.getStudentEntity();
				LaundryDTO laundryDTO = modelMapper.map(laundryEntity, LaundryDTO.class);
                StudentDTO studentDTO = modelMapper.map(studentEntity, StudentDTO.class);
                laundryDTO.setStudentObject(studentDTO);
                result.add(laundryDTO);
			}
			return ResponseEntity.ok(result);
		} catch (Exception e) {
            System.out.println(e.toString());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseObject("fail", "Internal Server Error!!! " + e.toString()));
        }
	}
	
	@Override
	public ResponseEntity<?> delete(long id) {
		try {
			laundryRepository.deleteById(id);
            return ResponseEntity.ok(new ResponseObject("ok", "Xóa lần giặt là thành công"));
        } catch (Exception e) {
            System.out.println(e.toString());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseObject("fail", "Internal Server Error!!! " + e.toString()));
        }
	}

	@Override
	public ResponseEntity<?> getBill() {
		try {
			List<LaundryEntity> laundryEntities = laundryRepository.findAll();
			long temp = 0;
			for (LaundryEntity laundryEntity : laundryEntities) {
				temp += laundryEntity.getPrice();
			}
			System.out.println("aaa");
			return ResponseEntity.ok(new ResponseObject("ok", "thành công", temp));
		} catch (Exception e) {
			System.out.println(e.toString());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseObject("fail", "Internal Server Error!!! " + e.toString()));
		}
		
		
	}
	
}