package demo.repository;

import demo.entity.BillEntity;
import demo.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Junkie on 09/12/2021.
 **/
public interface BillRepository extends JpaRepository<BillEntity, Long> {
    BillEntity findByStudentEntityAndMonthAndYear(StudentEntity studentEntity, int month, int year);
}
