package demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import demo.entity.LaundryEntity;

public interface LaundryRepository extends JpaRepository<LaundryEntity, Long> {
}