package demo.service;

import demo.dto.ParkingDTO;
import demo.dto.StudentDTO;
import demo.entity.ParkingEntity;
import demo.entity.ResponseObject;
import demo.entity.StudentEntity;
import demo.entity.VehicleEntity;
import demo.repository.ParkingRepository;
import demo.repository.StudentRepository;
import demo.repository.VehicleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by Junkie on 01/12/2021.
 **/
@Service
public class ParkingServiceImpl implements ParkingService {

    private final float PARKING_FEE = 3000;
    private final float PARKING_TICKET = 100000;

    @Autowired
    private ParkingRepository parkingRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseEntity<?> create(ParkingDTO dto) {
        try {
            ParkingEntity parkingEntity = modelMapper.map(dto, ParkingEntity.class);
            Optional<StudentEntity> studentEntity = studentRepository.findById(dto.getStudentId());
            if (!studentEntity.isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject("fail", "Sinh viên không tồn tại"));
            }
            Optional<VehicleEntity> vehicleEntity = Optional.ofNullable(vehicleRepository.findByStudentEntity(studentEntity.get()));
            LocalDate today = LocalDate.now();
            LocalDate tomorrow = today.plusDays(1);
            Date todayWithoutTime = Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant());
            Date tomorrowWithoutTime = Date.from(tomorrow.atStartOfDay(ZoneId.systemDefault()).toInstant());
            List<ParkingEntity> parkingEntities = parkingRepository.findAllByStartTimeBetweenAndNumberPlate(todayWithoutTime, tomorrowWithoutTime, dto.getNumberPlate());
            if (vehicleEntity.isPresent() && dto.getNumberPlate().equals(vehicleEntity.get().getNumberPlate())) {
                // Parking entity is not stored so plus one
                if (parkingEntities.size() + 1 < 3) {
                    parkingEntity.setStudentEntity(studentEntity.get());
                    parkingEntity.setPrice(0);
                    if (!vehicleEntity.get().isHasTicket()) {
                        parkingEntity.setPrice(PARKING_FEE);
                    }
                    parkingRepository.save(parkingEntity);
                } else {
                    parkingEntity.setStudentEntity(studentEntity.get());
                    parkingEntity.setPrice(PARKING_FEE);
                    parkingRepository.save(parkingEntity);
                }
            } else if (vehicleEntity.isPresent() && !dto.getNumberPlate().equals(vehicleEntity.get().getNumberPlate())) {
                parkingEntity.setStudentEntity(studentEntity.get());
                if (parkingEntities.size() + 1 < 3) {
                    parkingEntity.setPrice(0);
                } else {
                    parkingEntity.setPrice(PARKING_FEE);
                }
                parkingRepository.save(parkingEntity);
            } else {
                parkingEntity.setStudentEntity(studentEntity.get());
                parkingEntity.setPrice(PARKING_FEE);
                parkingRepository.save(parkingEntity);
            }
            ParkingDTO responseDto = modelMapper.map(parkingEntity, ParkingDTO.class);
            responseDto.setStudentObject(modelMapper.map(studentEntity.get(), StudentDTO.class));
            return ResponseEntity.ok(new ResponseObject("ok", "Thêm lượt xe thành công", responseDto));
        } catch (Exception e) {
            System.out.println(e.toString());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseObject("fail", "Internal Server Error!!! " + e.toString()));
        }
    }

    @Override
    public ResponseEntity<?> update(ParkingDTO dto) {
        try {
            Optional<ParkingEntity> parkingEntity = parkingRepository.findById(dto.getId());
            if (parkingEntity.isPresent()) {
                ParkingEntity parkingEntityUpdate = parkingEntity.get();
                if (dto.getServiceCode() != null) {
                    parkingEntityUpdate.setServiceCode(dto.getServiceCode());
                }
                if (dto.getName() != null) {
                    parkingEntityUpdate.setName(dto.getName());
                }
                if (dto.getNumberPlate() != null) {
                    parkingEntityUpdate.setNumberPlate(dto.getNumberPlate());
                }
                if (dto.isMoving()) {
                    parkingEntityUpdate.setMoving(dto.isMoving());
                }
                ParkingEntity parkingResponse = parkingRepository.save(parkingEntityUpdate);
                ParkingDTO responseDto = modelMapper.map(parkingResponse, ParkingDTO.class);
                StudentDTO studentDTO = modelMapper.map(parkingResponse.getStudentEntity(), StudentDTO.class);
                responseDto.setStudentObject(studentDTO);
                return ResponseEntity.ok(new ResponseObject("ok", "Cập nhật lượt xe thành công", responseDto));
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseObject("fail", "Cập nhật lượt xe không thành công"));
        } catch (Exception e) {
            System.out.println(e.toString());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseObject("fail", "Internal Server Error!!! " + e.toString()));
        }
    }

    @Override
    public ResponseEntity<?> getAll() {
        try {
            List<ParkingDTO> result = new ArrayList<>();
            List<ParkingEntity> parkingEntities = parkingRepository.findAllByOrderByStartTimeDesc();
            for (ParkingEntity parkingEntity : parkingEntities) {
                StudentEntity studentEntity = parkingEntity.getStudentEntity();
                ParkingDTO parkingDTO = modelMapper.map(parkingEntity, ParkingDTO.class);
                StudentDTO studentDTO = modelMapper.map(studentEntity, StudentDTO.class);
                parkingDTO.setStudentObject(studentDTO);
                result.add(parkingDTO);
            }
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            System.out.println(e.toString());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseObject("fail", "Internal Server Error!!! " + e.toString()));
        }
    }

    // Khong xoa duoc, cuu voi
    @Override
    public ResponseEntity<?> delete(long id) {
        try {
            parkingRepository.deleteById(id);
            return ResponseEntity.ok(new ResponseObject("ok", "Xóa gửi xe thành công"));
        } catch (Exception e) {
            System.out.println(e.toString());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseObject("fail", "Internal Server Error!!! " + e.toString()));
        }
    }

	@Override
	public ResponseEntity<?> getBill() {
		try {
			List<VehicleEntity> vehicleEntities = vehicleRepository.findAll();
			List<ParkingEntity> parkingEntities = parkingRepository.findAll();
			long temp = 0;
			for (VehicleEntity vehicleEntity: vehicleEntities) {
				if(vehicleEntity.isHasTicket() == true) {
					temp += PARKING_TICKET;
				}
			}
			for (ParkingEntity parkingEntity : parkingEntities) {
				temp += parkingEntity.getPrice();
			}
			return ResponseEntity.ok(new ResponseObject("ok", "thành công", temp));
		} catch (Exception e) {
			System.out.println(e.toString());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseObject("fail", "Internal Server Error!!! " + e.toString()));
		}
		
	}
}
