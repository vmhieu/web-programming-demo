package demo.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import demo.dto.AdminDTO;
import demo.entity.ResponseObject;

public interface AdminService {
	ResponseEntity<ResponseObject> checkAdmin(AdminDTO adminDTO); 
}
