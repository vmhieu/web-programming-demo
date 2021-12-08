package demo.controller;

import demo.dto.ParkingDTO;
import demo.service.ParkingService;
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

    @GetMapping("/parking")
    public ResponseEntity<?> getAllParking() {
        return parkingService.getAll();
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
}
