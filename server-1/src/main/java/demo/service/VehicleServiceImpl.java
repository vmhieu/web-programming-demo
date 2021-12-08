package demo.service;

import demo.dto.ParkingDTO;
import demo.dto.StudentDTO;
import demo.dto.VehicleDTO;
import demo.entity.ParkingEntity;
import demo.entity.ResponseObject;
import demo.entity.StudentEntity;
import demo.entity.VehicleEntity;
import demo.repository.StudentRepository;
import demo.repository.VehicleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Junkie on 08/12/2021.
 **/
@Service
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseEntity<?> create(VehicleDTO dto) {
        try {
            boolean hasNumberPlate = vehicleRepository.existsByNumberPlate(dto.getNumberPlate());
            if (hasNumberPlate) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ResponseObject("fail", "Biển số xe đã tồn tại"));
            } else {
                Optional<StudentEntity> studentEntity = studentRepository.findById(dto.getStudentId());
                Optional<VehicleEntity> vehicleEntityCurrent = Optional.ofNullable(vehicleRepository.findByStudentEntity(studentEntity.get()));
                if (vehicleEntityCurrent.isPresent()) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body(new ResponseObject("fail", "Sinh viên đã sở hữu xe"));
                } else {
                    VehicleEntity vehicleEntity = modelMapper.map(dto, VehicleEntity.class);
                    vehicleEntity.setStudentEntity(studentEntity.get());
                    VehicleEntity vehicleResponse = vehicleRepository.save(vehicleEntity);
                    VehicleDTO responseDto = modelMapper.map(vehicleResponse, VehicleDTO.class);
                    responseDto.setStudentObject(modelMapper.map(studentEntity.get(), StudentDTO.class));
                    return ResponseEntity.ok(new ResponseObject("ok", "Thêm xe thành công", responseDto));
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseObject("fail", "Internal Server Error!!! " + e.toString()));
        }
    }

    @Override
    public ResponseEntity<?> update(VehicleDTO dto) {
        try {
            Optional<VehicleEntity> vehicleEntity = vehicleRepository.findById(dto.getId());
            if (!vehicleEntity.isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ResponseObject("fail", "Cập nhật xe không thành công"));
            }
            boolean hasNumberPlate = vehicleRepository.existsByNumberPlate(dto.getNumberPlate());
            if (hasNumberPlate &&
                    !dto.getNumberPlate().equals(vehicleEntity.get().getNumberPlate())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ResponseObject("fail", "Biển số xe đã tồn tại"));
            } else {
                Optional<StudentEntity> studentEntity = studentRepository.findById(dto.getStudentId());
                Optional<VehicleEntity> vehicleEntityByStudent = Optional.ofNullable(vehicleRepository.findByStudentEntity(studentEntity.get()));
                // Update when studentId not change or studentId has not vehicle
                if (dto.getStudentId().equals(vehicleEntity.get().getStudentEntity().getId()) ||
                        !vehicleEntityByStudent.isPresent()) {
                    VehicleEntity vehicleEntityUpdate = vehicleEntity.get();
                    if (dto.getNumberPlate() != null) {
                        vehicleEntityUpdate.setNumberPlate(dto.getNumberPlate());
                    }
                    vehicleEntityUpdate.setHasTicket(dto.isHasTicket());
                    if (dto.getStudentId() != null) {
                        Optional<StudentEntity> studentEntityNew = studentRepository.findById(dto.getStudentId());
                        vehicleEntityUpdate.setStudentEntity(studentEntityNew.get());
                    }
                    VehicleEntity vehicleResponse = vehicleRepository.save(vehicleEntityUpdate);
                    VehicleDTO responseDto = modelMapper.map(vehicleResponse, VehicleDTO.class);
                    StudentDTO studentDTO = modelMapper.map(vehicleResponse.getStudentEntity(), StudentDTO.class);
                    responseDto.setStudentObject(studentDTO);
                    return ResponseEntity.ok(new ResponseObject("ok", "Cập nhật xe thành công", responseDto));
                } else {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body(new ResponseObject("fail", "Sinh viên đã sở hữu xe"));
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseObject("fail", "Internal Server Error!!! " + e.toString()));
        }
    }

    @Override
    public ResponseEntity<?> getAll() {
        try {
            List<VehicleDTO> result = new ArrayList<>();
            List<VehicleEntity> vehicleEntities = vehicleRepository.findAll();
            for (VehicleEntity item : vehicleEntities) {
                StudentEntity studentEntity = item.getStudentEntity();
                VehicleDTO vehicleDTO = modelMapper.map(item, VehicleDTO.class);
                StudentDTO studentDTO = modelMapper.map(studentEntity, StudentDTO.class);
                vehicleDTO.setStudentObject(studentDTO);
                result.add(vehicleDTO);
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
            vehicleRepository.deleteById(id);
            return ResponseEntity.ok(new ResponseObject("ok", "Xóa xe thành công"));
        } catch (Exception e) {
            System.out.println(e.toString());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseObject("fail", "Internal Server Error!!! " + e.toString()));
        }
    }
}
