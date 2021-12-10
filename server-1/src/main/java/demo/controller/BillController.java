package demo.controller;

import demo.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * Created by Junkie on 09/12/2021.
 **/
@CrossOrigin
@RestController
@RequestMapping("/api/bill")
public class BillController {
    @Autowired
    private BillService billService;

    @GetMapping
    public ResponseEntity<?> getBill(@RequestParam long studentId) {
        return billService.getBillByStudentId(studentId);
    }
}
