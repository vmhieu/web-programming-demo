package demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import demo.dto.AdminDTO;
import demo.entity.ResponseObject;
import demo.service.AdminService;

@CrossOrigin
@RestController
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@PostMapping("/api/admin")
	ResponseEntity<ResponseObject> checkAdmin(@RequestBody AdminDTO adminDTO){
		return adminService.checkAdmin(adminDTO);
	}
}
