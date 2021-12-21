package demo.service;

import demo.dto.FoodDTO;
import demo.dto.StudentDTO;
import demo.entity.FoodEntity;
import demo.entity.ResponseObject;
import demo.entity.StudentEntity;
import demo.repository.StudentRepository;
import demo.repository.FoodRepository;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FoodServiceImpl implements FoodService {
	
	@Autowired
    private StudentRepository studentRepository;
	
	@Autowired
	private FoodRepository foodRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public ResponseEntity<?> create(FoodDTO dto) {
			FoodEntity foodEntity = modelMapper.map(dto, FoodEntity.class);
			Optional<StudentEntity> studentEntity = studentRepository.findById(dto.getStudentId());
			System.out.println(dto.getStudentId());
			if (!studentEntity.isPresent()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject("fail", "Sinh viên không tồn tại"));
			} else {
				foodEntity.setStudentEntity(studentEntity.get());
				foodEntity.setTimes(dto.getTimes());
				foodEntity.setPrice(dto.getPriceUnit() * (float) dto.times);
				foodRepository.save(foodEntity);
			}
			return ResponseEntity.ok(new ResponseObject("ok", "Thêm lần ăn thành công", ""));
	}
	
	@Override
	public ResponseEntity<?> update(FoodDTO dto) {
			Optional<FoodEntity> foodEntity = foodRepository.findById(dto.getId());
			Optional<StudentEntity> studentEntity = studentRepository.findById(dto.getStudentId());
			if (foodEntity.isPresent()) {
				FoodEntity foodEntityUpdate = foodEntity.get();
				if (dto.getServiceCode() != null) {
                    foodEntityUpdate.setServiceCode(dto.getServiceCode());
                }
                if (dto.getName() != null) {
                    foodEntityUpdate.setName(dto.getName());
                }
                foodEntityUpdate.setPrice(dto.getPriceUnit() * (float) dto.getTimes());
                foodEntityUpdate.setTimes(dto.getTimes());
//                }
				FoodEntity foodResponse = foodRepository.save(foodEntityUpdate);
                FoodDTO responseDto = modelMapper.map(foodResponse, FoodDTO.class);
                StudentDTO studentDTO = modelMapper.map(foodResponse.getStudentEntity(), StudentDTO.class);
                responseDto.setStudentObject(studentDTO);
				return ResponseEntity.ok(new ResponseObject("ok", "Cập nhật lượt ăn thành công", ""));
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseObject("fail", "Cập nhật lượt ăn không thành công"));
	}
	
	@Override
	public ResponseEntity<?> getAll() {
		try {
			List<FoodDTO> result = new ArrayList<>();
			List<FoodEntity> foodEntities = foodRepository.findAll();
			for (FoodEntity foodEntity : foodEntities) {
				StudentEntity studentEntity = foodEntity.getStudentEntity();
				FoodDTO foodDTO = modelMapper.map(foodEntity, FoodDTO.class);
                StudentDTO studentDTO = modelMapper.map(studentEntity, StudentDTO.class);
                foodDTO.setStudentObject(studentDTO);
                result.add(foodDTO);
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
            foodRepository.deleteById(id);
            return ResponseEntity.ok(new ResponseObject("ok", "Xóa lần ăn thành công"));
        } catch (Exception e) {
            System.out.println(e.toString());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseObject("fail", "Internal Server Error!!! " + e.toString()));
        }
	}

	@Override
	public ResponseEntity<?> getBill() {
		try {
			List<FoodEntity> foodEntities = foodRepository.findAll();
			long temp = 0;
			for (FoodEntity foodEntity : foodEntities) {
				temp += foodEntity.getPrice();
			}
			
			return ResponseEntity.ok(new ResponseObject("ok", "thành công", temp));
		} catch (Exception e) {
			System.out.println(e.toString());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseObject("fail", "Internal Server Error!!! " + e.toString()));
		}
		
		
	}
}