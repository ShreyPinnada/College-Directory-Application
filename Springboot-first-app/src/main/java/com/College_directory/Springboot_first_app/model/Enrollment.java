package com.College_directory.Springboot_first_app.model;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "enrollment")
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private StudentProfile student;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    public void setStudentId(Long studentId) {
    }

    public void setCourseId(Long courseId) {

    }
}
