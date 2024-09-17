package com.College_directory.Springboot_first_app.service;

import com.College_directory.Springboot_first_app.dto.enrollment.EnrollmentCreateDTO;
import com.College_directory.Springboot_first_app.model.Enrollment;

import java.util.List;

public interface EnrollmentInterface {
    // Create
    Enrollment enrollStudent(Long userId, EnrollmentCreateDTO enrollmentCreateDTO);

    // Read
    Enrollment getEnrollmentById(Long id);
    List<Enrollment> getEnrollmentsByStudent(Long studentId);
    List<Enrollment> getEnrollmentsByCourse(Long courseId);

    // Delete
    boolean unEnrollStudent(Long studentId, Long courseId);
}
