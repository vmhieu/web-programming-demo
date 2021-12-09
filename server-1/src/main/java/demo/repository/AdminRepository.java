package demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.entity.AdminEntity;

public interface AdminRepository extends JpaRepository<AdminEntity, Long>{
	AdminEntity findByUsername(String username);
}
