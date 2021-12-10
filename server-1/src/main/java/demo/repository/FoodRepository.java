package demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import demo.entity.FoodEntity;

public interface FoodRepository extends JpaRepository<FoodEntity, Long> {
}