package com.example.demo.dao;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Table(name = "app_user_role")
@Data
public class AppUserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "app_user_id", nullable = false)
    private AppUser appUser;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;
}
