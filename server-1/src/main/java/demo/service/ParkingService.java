package demo.service;

import demo.dto.ParkingDTO;
import org.springframework.http.ResponseEntity;

/**
 * Created by Junkie on 01/12/2021.
 **/
public interface ParkingService {
    ResponseEntity<?> create(ParkingDTO dto);
    ResponseEntity<?> update(ParkingDTO dto);
    ResponseEntity<?> getAll();
    ResponseEntity<?> getBill();
    ResponseEntity<?> delete(long id);
}
