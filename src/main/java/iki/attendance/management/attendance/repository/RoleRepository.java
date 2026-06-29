package iki.attendance.management.attendance.repository;

import iki.attendance.management.attendance.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    boolean existsByName(String role);
    Optional<Role> findByName(String name);
}
