package demo.service;

import demo.dto.LaundryDTO;
import org.springframework.http.ResponseEntity;

public interface LaundryService {
    ResponseEntity<?> create(LaundryDTO dto);
    ResponseEntity<?> update(LaundryDTO dto);
    ResponseEntity<?> getAll();
    ResponseEntity<?> getBill();
    ResponseEntity<?> delete(long id);
}
