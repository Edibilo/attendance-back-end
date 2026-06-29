package iki.attendance.management.attendance.repository;

import iki.attendance.management.attendance.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
}
