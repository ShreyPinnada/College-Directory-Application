package com.College_directory.Springboot_first_app.model;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @ManyToOne
    @JoinColumn(name = "faculty_id", nullable = false)
    private FacultyProfile faculty;
}
