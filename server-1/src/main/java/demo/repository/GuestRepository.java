package demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import demo.entity.GuestEntity;

public interface GuestRepository extends JpaRepository<GuestEntity, Long>{

}
