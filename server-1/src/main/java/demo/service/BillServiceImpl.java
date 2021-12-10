package demo.service;

import demo.dto.BillDTO;
import demo.dto.FoodDTO;
import demo.dto.InvoiceDTO;
import demo.dto.LaundryDTO;
import demo.dto.ParkingDTO;
import demo.entity.*;
import demo.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
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

    private final float TICKET_FEE = 100000;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ParkingRepository parkingRepository;

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private LaundryRepository laundryRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public ResponseEntity<?> getBillByStudentId(long studentId) {
        try {
            Optional<StudentEntity> studentEntity = studentRepository.findById(studentId);
            if (studentEntity.isPresent()) {
                List<BillDTO> result = new ArrayList<>();
                LocalDate firstDate = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
                LocalDate lastDate = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth()).plusDays(1);
                Date firstDateWithoutTime = Date.from(firstDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                Date lastDateWithoutTime = Date.from(lastDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                List<ServiceEntity> serviceEntities = serviceRepository.findByStudentEntityAndCreatedDateBetween(studentEntity.get(), firstDateWithoutTime, lastDateWithoutTime);
                // Get service for a month
                RoomEntity roomEntity = roomRepository.findByStudentRoom(studentEntity.get());
                VehicleEntity vehicleEntity = vehicleRepository.findByStudentEntity(studentEntity.get());
                float totalPrice = roomEntity.getPriceUnit();
                if (vehicleEntity.isHasTicket()) {
                    totalPrice += TICKET_FEE;
                }
                for (ServiceEntity item : serviceEntities) {
                    totalPrice += item.getPrice();
                    System.out.println(item.getId());
                    BillDTO billDTO = new BillDTO();
                    String[] type = item.getClass().getName().split("\\.");
                    billDTO.setType(type[2]);
                    billDTO.setCreatedDate(item.getCreatedDate());
                    if (type[2].equals("ParkingEntity")) {
                        Optional<ParkingEntity> parkingEntity = parkingRepository.findById(item.getId());
                        if (parkingEntity.isPresent()) {
                            ParkingDTO parkingDTO = modelMapper.map(parkingEntity.get(), ParkingDTO.class);
                            billDTO.setObject(parkingDTO);
                        }
                    } else if (type[2].equals("FoodEntity")) {
                        Optional<FoodEntity> foodEntity = foodRepository.findById(item.getId());
                        if (foodEntity.isPresent()) {
                            FoodDTO foodDTO = modelMapper.map(foodEntity.get(), FoodDTO.class);
                            billDTO.setObject(foodDTO);
                        }
                    } else if (type[2].equals("LaundryEntity")) {
                        Optional<LaundryEntity> laundryEntity = laundryRepository.findById(item.getId());
                        if (laundryEntity.isPresent()) {
                            LaundryDTO laundryDTO = modelMapper.map(laundryEntity.get(), LaundryDTO.class);
                            billDTO.setObject(laundryDTO);
                        }
                    }
                    result.add(billDTO);
                }
                // Save bill
                int month = LocalDate.now().getMonthValue();
                int year = LocalDate.now().getYear();
                Optional<BillEntity> billEntity = Optional.ofNullable(billRepository.findByStudentEntityAndMonthAndYear(studentEntity.get(), month, year));
                if (billEntity.isPresent()) {
                    billEntity.get().setTotalPrice(totalPrice);
                    billRepository.save(billEntity.get());
                } else {
                    BillEntity newBillEntity = new BillEntity();
                    newBillEntity.setMonth(month);
                    newBillEntity.setYear(year);
                    newBillEntity.setTotalPrice(totalPrice);
                    newBillEntity.setStudentEntity(studentEntity.get());
                    billRepository.save(newBillEntity);
                }
                // response
                InvoiceDTO responseDto = new InvoiceDTO();
                responseDto.setMonth(month);
                responseDto.setYear(year);
                responseDto.setName(studentEntity.get().getName());
                responseDto.setStudentCode(studentEntity.get().getStudentCode());
                responseDto.setRoomFee(roomEntity.getPriceUnit());
                if (vehicleEntity.isHasTicket()) {
                    responseDto.setTicketFee(TICKET_FEE);
                }
                responseDto.setTotalPrice(totalPrice);
                responseDto.setBill(result);

                return ResponseEntity.ok(responseDto);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseObject("fail", "Fail"));
        } catch (Exception e) {
            System.out.println(e.toString());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseObject("fail", "Internal Server Error!!! " + e.toString()));
        }
    }
}
