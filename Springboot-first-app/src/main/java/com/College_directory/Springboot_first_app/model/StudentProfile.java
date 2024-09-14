package com.College_directory.Springboot_first_app.model;



import jakarta.persistence.*;
import lombok.Data;



@Data
@Entity
@Table(name = "studentprofile")
public class StudentProfile {
    @Id
    private Long Id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    private String photo;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    private String year;
}