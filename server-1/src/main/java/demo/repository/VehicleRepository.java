package demo.repository;

import demo.entity.StudentEntity;
import demo.entity.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Junkie on 06/12/2021.
 **/
public interface VehicleRepository extends JpaRepository<VehicleEntity, Long> {
    VehicleEntity findByStudentEntity(StudentEntity studentEntity);
    boolean existsByNumberPlate(String numberPlate);
}
