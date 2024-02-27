package com.example.demo.dao;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.Set;

/// validate, update, create, create-drop, none
@Entity
@Table(name = "app_user")
@Data
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String username;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(nullable = false)
    private Boolean active;

    @Column(name = "creation_date")
    @Temporal(TemporalType.DATE)
    private Date creationDate;

    @Column(name = "update_date")
    @Temporal(TemporalType.DATE)
    private Date updateDate;

    @OneToMany(mappedBy = "appUser")
    private Set<AppUserRole> appUserRoles;
}
