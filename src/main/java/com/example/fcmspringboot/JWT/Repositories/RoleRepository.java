package com.example.fcmspringboot.JWT.Repositories;

import com.example.fcmspringboot.JWT.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String role);
}
