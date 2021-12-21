package demo.controller;

import demo.dto.ParkingDTO;
import demo.dto.FoodDTO;
import demo.dto.LaundryDTO;
import demo.service.ParkingService;
import demo.service.FoodService;
import demo.service.LaundryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Junkie on 01/12/2021.
 **/
@RestController
@CrossOrigin
@RequestMapping("/api/service")
public class ServiceController {
    @Autowired
    private ParkingService parkingService;
    
    @Autowired
    private FoodService foodService;
    
    @Autowired
    private LaundryService laundryService;

    @GetMapping("/parking")
    public ResponseEntity<?> getAllParking() {
        return parkingService.getAll();
    }
    
    @GetMapping("/bill/parking")
    public ResponseEntity<?> getBillParking() {
        return parkingService.getBill();
    }

    @PostMapping("/parking")
    public ResponseEntity<?> createParking(@RequestBody ParkingDTO dto) {
        return parkingService.create(dto);
    }

    @PutMapping("/parking/{id}")
    public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody ParkingDTO dto) {
        dto.setId(id);
        return parkingService.update(dto);
    }

    @DeleteMapping("/parking/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        return parkingService.delete(id);
    }
    
    @PostMapping("/food")
    public ResponseEntity<?> createFood(@RequestBody FoodDTO dto) {
    	return foodService.create(dto);
    }
    
    @PutMapping("/food/{id}")
    public ResponseEntity<?> updateFood(@PathVariable("id") long id, @RequestBody FoodDTO dto) {
    	dto.setId(id);
    	return foodService.update(dto);
    }
    
    @GetMapping("/food")
    public ResponseEntity<?> getAllFood() {
        return foodService.getAll();
    }
    
    @GetMapping("/bill/food")
    public ResponseEntity<?> getBillFood() {
        return foodService.getBill();
    }
    
    @DeleteMapping("/food/{id}")
    public ResponseEntity<?> deleteFood(@PathVariable long id) {
        return foodService.delete(id);
    }
    
    @PostMapping("/laundry")
    public ResponseEntity<?> createLaundry(@RequestBody LaundryDTO dto) {
    	return laundryService.create(dto);
    }
    
    @PutMapping("/laundry/{id}")
    public ResponseEntity<?> updateLaundry(@PathVariable("id") long id, @RequestBody LaundryDTO dto) {
    	dto.setId(id);
    	return laundryService.update(dto);
    }
    
    @GetMapping("/laundry")
    public ResponseEntity<?> getAllLaundry() {
        return laundryService.getAll();
    }
    
    @GetMapping("/bill/laundry")
    public ResponseEntity<?> getBillLaundry() {
        return laundryService.getBill();
    }
    
    @DeleteMapping("/laundry/{id}")
    public ResponseEntity<?> deleteLaundry(@PathVariable long id) {
        return laundryService.delete(id);
    }
}
