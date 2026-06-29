package iki.attendance.management.attendance.repository;

import iki.attendance.management.attendance.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsByEmail(String email);
    Optional<User> findByNameOrEmail(String name,String email);
}
