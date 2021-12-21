package demo.service.room;

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
import demo.entity.ResponseObject;
import demo.entity.RoomEntity;
import demo.entity.StudentEntity;
import demo.repository.RoomRepository;
import demo.repository.StudentRepository;

@Service
public class RoomServiceImpl implements RoomService {

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<RoomDTO> getAll() {
		List<RoomDTO> result = new ArrayList<>();
		List<RoomEntity> roomEntity = roomRepository.findAll();
		List<StudentEntity> studentEtt = studentRepository.findAll();
		for (RoomEntity items : roomEntity) {
			int count = 0;
			RoomDTO roomDTO = modelMapper.map(items, RoomDTO.class);
			for (StudentEntity studentEntity : studentEtt) {
				if (roomDTO.getId() == studentEntity.getRoom().getId()) {
					count++;
				}
			}
			roomDTO.setTotal(count);
			items.setTotal(count);
			roomRepository.save(items);
			result.add(roomDTO);
//			for (RoomDTO item : result) {
//				RoomEntity roomEtt = modelMapper.map(item , RoomEntity.class);
//				roomRepository.save(roomEtt);
//			}
		}
		return result;
	}

	@Override
	public ResponseEntity<ResponseObject> create(RoomDTO roomDTO) {
		// TODO Auto-generated method stub
		boolean has = roomRepository.existsByName(roomDTO.getName());
		if(has) {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new ResponseObject("fail", "Tên phòng đã tồn tại"));
		}
		else {
			RoomEntity roomEtt = new RoomEntity();
			roomEtt = modelMapper.map(roomDTO, RoomEntity.class);

			roomEtt = roomRepository.save(roomEtt);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "Them moi phong thanh cong"));
		}
	}

	@Override
	public ResponseEntity<ResponseObject> findById(long id) {
		List<RoomEntity> listRoom = roomRepository.findAll();
		int count =0;
		for (RoomEntity roomEntity : listRoom) {
			if(roomEntity.getId() == id) {
				count = 1;
				break;
			}
		}
		if(count == 1) {
			RoomEntity roomEtt = roomRepository.findById(id).get();
			RoomDTO roomDTO = modelMapper.map(roomEtt, RoomDTO.class);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "Get successfully", roomDTO));
		}
			
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseObject("failed", "Khong tim thay phong", ""));
		}
	}

	@Override
	public ResponseEntity<ResponseObject> update(RoomDTO roomDTO) {
//		RoomEntity roomEtt = roomRepository.findById(roomDTO.getId()).get();
		if (roomRepository.findById(roomDTO.getId()).isPresent()) {
			RoomEntity roomEtt = roomRepository.findById(roomDTO.getId()).get();
			roomEtt = modelMapper.map(roomDTO, RoomEntity.class);
			roomRepository.save(roomEtt);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("ok", "Insert Guest successfully", roomDTO));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
					.body(new ResponseObject("failed", "Successfully"));
		}
	}

	@Override
	public ResponseEntity<ResponseObject> deleteById(long id) {
		RoomEntity roomEtt = roomRepository.findById(id).get();
		if (roomEtt.getId() != 0) {
			roomRepository.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "Delete successfully"));
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("failed", "Not Found"));
		}
	}

	@Override
	public ResponseEntity<?> findByName(String name) {
		List<RoomDTO> results = new ArrayList<>();
		List<RoomEntity> studentEntities = roomRepository.findByNameIgnoreCaseContaining(name);
		if(!studentEntities.isEmpty()) {
			for (RoomEntity item : studentEntities) {
//				RoomEntity roomEntity = item.getRoom();
//				StudentDTO studentDTO = modelMapper.map(item, StudentDTO.class);
				RoomDTO roomDTO = modelMapper.map(item, RoomDTO.class);
//				studentDTO.setRoomObject1(roomDTO);
				results.add(roomDTO);
			}
			return  ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "Thành công", results));
		} else {
			return  ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("failed", "Không tìm thấy phòng"));
		}
	
	}

	@Override
	public ResponseEntity<?> getBillRoom() {
		List<RoomEntity> roomEntity = roomRepository.findAll();
		int count=0;
		for (RoomEntity items : roomEntity) {
			System.out.println("total " + items.getTotal());
			count += items.getTotal()*items.getPriceUnit();
		}
		System.out.println("count " + count);
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("ok", "Thành công", count));
	}

}
