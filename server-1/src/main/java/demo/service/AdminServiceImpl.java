package demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import demo.dto.AdminDTO;
import demo.entity.AdminEntity;
import demo.entity.ResponseObject;
import demo.repository.AdminRepository;

@Service
public class AdminServiceImpl implements AdminService{

	@Autowired
	private AdminRepository adminRepository;
	

	@Override
	public ResponseEntity<ResponseObject> checkAdmin(AdminDTO adminDTO) {
		// TODO Auto-generated method stub
		AdminEntity adminEtt = adminRepository.findByUsername(adminDTO.getUsername());
		System.out.println(adminEtt.getUsername() + adminDTO.getUsername());
		if(adminDTO.getPassword().equals(adminEtt.getPassword())) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject("OK", "Đăng nhập thành công"));
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ResponseObject("Fail", "Không tồn tại user"));
		}
	}

}
