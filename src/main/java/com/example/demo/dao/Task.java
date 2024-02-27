package com.example.demo.dao;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
@Entity
@Table(name = "task")
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "creation_date")
    @Temporal(TemporalType.DATE)
    private Date creationDate;

    @Column(name = "update_date")
    @Temporal(TemporalType.DATE)
    private Date updateDate;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private AppUser author;
}
