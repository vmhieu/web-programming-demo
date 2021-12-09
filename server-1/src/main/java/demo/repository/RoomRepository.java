package demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.entity.RoomEntity;

public interface RoomRepository extends JpaRepository<RoomEntity, Long>{
	
}
