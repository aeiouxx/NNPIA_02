package com.example.demo.repository;

import com.example.demo.dao.AppUserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRoleRepository extends JpaRepository<AppUserRole, Long> {
}
