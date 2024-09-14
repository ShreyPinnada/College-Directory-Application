package com.College_directory.Springboot_first_app.service;

import com.College_directory.Springboot_first_app.model.Enrollment;

import java.util.List;

public interface EnrollmentInterface {
    // Create
    Enrollment enrollStudent(Long studentId, Long courseId);

    // Read
    Enrollment getEnrollmentById(Long id);
    List<Enrollment> getEnrollmentsByStudent(Long studentId);
    List<Enrollment> getEnrollmentsByCourse(Long courseId);

    // Delete
    boolean unenrollStudent(Long studentId, Long courseId);
}
