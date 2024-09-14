package com.College_directory.Springboot_first_app.model;


import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "appuser")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    private String phone;
}

enum Role {
    STUDENT, FACULTY_MEMBER, ADMINISTRATOR
}