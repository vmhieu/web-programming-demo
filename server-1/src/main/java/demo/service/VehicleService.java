package demo.service;

import demo.dto.VehicleDTO;
import org.springframework.http.ResponseEntity;

/**
 * Created by Junkie on 08/12/2021.
 **/
public interface VehicleService {
    ResponseEntity<?> create(VehicleDTO dto);
    ResponseEntity<?> update(VehicleDTO dto);
    ResponseEntity<?> getAll();
    ResponseEntity<?> delete(long id);
}
