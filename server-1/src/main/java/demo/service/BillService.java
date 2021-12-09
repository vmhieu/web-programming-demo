package demo.service;

import org.springframework.http.ResponseEntity;

/**
 * Created by Junkie on 09/12/2021.
 **/
public interface BillService {
    ResponseEntity<?> getBillByStudentId(long studentId);
}
