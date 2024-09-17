package com.College_directory.Springboot_first_app.controller;

import com.College_directory.Springboot_first_app.dto.enrollment.EnrollmentCreateDTO;
import com.College_directory.Springboot_first_app.model.Enrollment;
import com.College_directory.Springboot_first_app.service.EnrollmentInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

    private final EnrollmentInterface enrollmentInterface;

    public EnrollmentController(EnrollmentInterface enrollmentInterface) {
        this.enrollmentInterface = enrollmentInterface;
    }

    @PostMapping("/create/{userId}")
    public ResponseEntity<Enrollment> enrollStudent(@PathVariable Long userId, @RequestBody EnrollmentCreateDTO enrollmentCreateDTO) {
        Enrollment enrollment = enrollmentInterface.enrollStudent(userId, enrollmentCreateDTO);
        return ResponseEntity.ok(enrollment);
    }

    @GetMapping("{id}")
    public ResponseEntity<Enrollment> getEnrollmentById(@PathVariable Long id) {
        Enrollment enrollment = enrollmentInterface.getEnrollmentById(id);
        return ResponseEntity.ok(enrollment);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Enrollment>> getEnrollmentsByStudent(@PathVariable Long studentId) {
        List<Enrollment> enrollments = enrollmentInterface.getEnrollmentsByStudent(studentId);
        return ResponseEntity.ok(enrollments);
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Enrollment>> getEnrollmentsByCourse(@PathVariable Long courseId) {
        List<Enrollment> enrollments = enrollmentInterface.getEnrollmentsByCourse(courseId);
        return ResponseEntity.ok(enrollments);
    }
    @DeleteMapping("/un-enroll")
    public ResponseEntity<Boolean> unEnrollStudent(@RequestParam Long studentId, @RequestParam Long courseId) {
        boolean result = enrollmentInterface.unEnrollStudent(studentId, courseId);
        return ResponseEntity.ok(result);
    }
}