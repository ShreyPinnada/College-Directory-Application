package com.College_directory.Springboot_first_app.model;



import jakarta.persistence.*;
import lombok.Data;
import org.apache.catalina.User;


@Data
@Entity
@Table(name = "studentprofile")
public class StudentProfile {
    @Id
    private Long userId;

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