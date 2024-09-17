package com.College_directory.Springboot_first_app.controller;

import com.College_directory.Springboot_first_app.dto.user.StudentProfileDTO;
import com.College_directory.Springboot_first_app.model.StudentProfile;
import com.College_directory.Springboot_first_app.service.StudentProfileServiceInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentProfileController {

    private final StudentProfileServiceInterface studentService;

    public StudentProfileController(StudentProfileServiceInterface studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/create/{userId}")
    public ResponseEntity<StudentProfile> createStudentProfile(@PathVariable Long userId, @RequestBody StudentProfileDTO studentProfileDTO) {
        StudentProfile studentProfile = studentService.createStudentProfile(userId, studentProfileDTO);
        return ResponseEntity.ok(studentProfile);
    }

    @GetMapping("{userId}")
    public ResponseEntity<StudentProfile> getStudentProfileByUserId(@PathVariable Long userId) {
        StudentProfile student = studentService.getStudentProfileById(userId);
        return ResponseEntity.ok(student);
    }

    @GetMapping
    public ResponseEntity<List<StudentProfile>> getAllStudents() {
        List<StudentProfile> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @PutMapping("{userId}")
    public ResponseEntity<StudentProfile> updateStudentProfile(@PathVariable Long userId, @RequestBody StudentProfileDTO studentProfileDTO) {
        StudentProfile studentProfile = studentService.updateStudentProfile(userId, studentProfileDTO);
        return ResponseEntity.ok(studentProfile);
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<String> deleteStudentProfile(@PathVariable Long userId) {
        boolean result = studentService.deleteStudentProfile(userId);
        if (result) {
            return ResponseEntity.ok("Student deleted successfully");
        } else {
            return ResponseEntity.status(400).body("Student deletion failed");
        }
    }

}
