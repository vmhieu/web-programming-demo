package demo.repository;

import demo.entity.ServiceEntity;
import demo.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by Junkie on 09/12/2021.
 **/
public interface ServiceRepository extends JpaRepository<ServiceEntity, Long> {
    List<ServiceEntity> findByStudentEntityAndCreatedDateBetween(StudentEntity studentEntity, Date fistDate, Date lastDate);
}
