package demo.service;

import demo.dto.FoodDTO;
import org.springframework.http.ResponseEntity;

public interface FoodService {
    ResponseEntity<?> create(FoodDTO dto);
    ResponseEntity<?> update(FoodDTO dto);
    ResponseEntity<?> getAll();
    ResponseEntity<?> getBill();
    ResponseEntity<?> delete(long id);
}
