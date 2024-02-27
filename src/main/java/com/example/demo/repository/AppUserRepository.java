package com.example.demo.repository;

import com.example.demo.dao.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
    @Query("SELECT au FROM AppUser au JOIN au.appUserRoles aur JOIN aur.role r WHERE r.name = :roleName")
    List<AppUser> findByRoleName(@Param("roleName") String roleName);
}
