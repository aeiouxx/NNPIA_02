package com.example.demo.dao;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;
import java.util.Set;

/// validate, update, create, create-drop, none
@Entity
@Table(name = "app_user")
@Data
public class AppUser {
    private static final int USERNAME_MAX_LENGTH = 255;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = USERNAME_MAX_LENGTH)
    @NotNull(message = "Username must be provided")
    @NotBlank(message = "Username must not be empty")
    @Size(max = USERNAME_MAX_LENGTH, message = "Username must not exceed 255 characters")
    private String username;

    @Column(nullable = false)
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
