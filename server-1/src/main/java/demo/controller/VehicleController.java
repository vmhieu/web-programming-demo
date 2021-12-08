package demo.controller;

import demo.dto.VehicleDTO;
import demo.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Junkie on 01/12/2021.
 **/
@CrossOrigin
@RestController
@RequestMapping("/api/vehicle")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return vehicleService.getAll();
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody VehicleDTO dto) {
        return vehicleService.create(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody VehicleDTO dto) {
        dto.setId(id);
        return vehicleService.update(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        return vehicleService.delete(id);
    }
}
