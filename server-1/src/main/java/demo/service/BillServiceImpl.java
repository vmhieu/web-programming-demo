package demo.service;

import demo.dto.BillDTO;
import demo.dto.ParkingDTO;
import demo.entity.*;
import demo.repository.BillRepository;
import demo.repository.ParkingRepository;
import demo.repository.ServiceRepository;
import demo.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by Junkie on 09/12/2021.
 **/
@Service
public class BillServiceImpl implements BillService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ParkingRepository parkingRepository;

    @Override
    public ResponseEntity<?> getBillByStudentId(long studentId) {
        Optional<StudentEntity> studentEntity = studentRepository.findById(studentId);
        if (studentEntity.isPresent()) {
            List<BillDTO> result = new ArrayList<>();
            LocalDate firstDate = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
            LocalDate lastDate = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth()).plusDays(1);
            Date firstDateWithoutTime = Date.from(firstDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            Date lastDateWithoutTime = Date.from(lastDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            List<ServiceEntity> serviceEntities = serviceRepository.findByStudentEntityAndCreatedDateBetween(studentEntity.get(), firstDateWithoutTime, lastDateWithoutTime);
            // Get service for a month
            float totalPrice = 0;
            for (ServiceEntity item : serviceEntities) {
                totalPrice += item.getPrice();
                System.out.println(item.getId());
                BillDTO billDTO = new BillDTO();
                String[] type = item.getClass().getName().split("\\.");
                billDTO.setType(type[2]);
                if (type[2].equals("ParkingEntity")) {
                    Optional<ParkingEntity> parkingEntity = parkingRepository.findById(item.getId());
                    if (parkingEntity.isPresent()) {
                        ParkingDTO parkingDTO = modelMapper.map(parkingEntity.get(), ParkingDTO.class);
                        billDTO.setObject(parkingDTO);
                        result.add(billDTO);
                    }
                }
                // Thieu 2 dich vu
                // Thieu cong them tiem phong
            }
            // Save bill
            BillEntity billEntity = new BillEntity();
            billEntity.setMonth(LocalDate.now().getMonthValue());
            billEntity.setYear(LocalDate.now().getYear());
            billEntity.setTotalPrice(totalPrice);
            billEntity.setStudentEntity(studentEntity.get());
            billRepository.save(billEntity);

            return ResponseEntity.ok(result);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ResponseObject("fail", "Fail"));
    }
}
