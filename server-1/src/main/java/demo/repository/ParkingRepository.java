package demo.repository;

import demo.entity.ParkingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by Junkie on 01/12/2021.
 **/
public interface ParkingRepository extends JpaRepository<ParkingEntity, Long> {
    List<ParkingEntity> findAllByStartTimeBetweenAndNumberPlate(Date minDate, Date maxDate, String numberPlate);
    List<ParkingEntity> findAllByOrderByStartTimeDesc();
}
