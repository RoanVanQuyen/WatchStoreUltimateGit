package com.example.watchstoreultimate.repository;

import com.example.watchstoreultimate.entity.Role;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
@Hidden
public interface RoleRepository extends JpaRepository<Role , Integer> {
}
