package iki.attendance.management.attendance.repository;

import iki.attendance.management.attendance.entity.Level;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRepository extends JpaRepository<Level,Long> {
    boolean existsByNameAndStream(String name,String stream);

}
