package com.College_directory.Springboot_first_app.controller;


import com.College_directory.Springboot_first_app.dto.course.CourseCreateDTO;
import com.College_directory.Springboot_first_app.dto.course.CourseUpdateDTO;
import com.College_directory.Springboot_first_app.model.Course;
import com.College_directory.Springboot_first_app.service.implement.CourseImplement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    private final CourseImplement courseImplement;
    public CourseController(CourseImplement courseImplement) {
        this.courseImplement = courseImplement;
    }

    @PostMapping("/create")
    public ResponseEntity<Course> createCourse(@RequestBody CourseCreateDTO courseCreateDTO) {
        Course course = courseImplement.createCourse(courseCreateDTO);
        return ResponseEntity.ok(course);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody CourseUpdateDTO courseUpdateDTO) {
        Course course = courseImplement.updateCourse(id, courseUpdateDTO);
        return ResponseEntity.ok(course);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        Course course = courseImplement.getCourseById(id);
        return ResponseEntity.ok(course);
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses(){
        List<Course> courses = courseImplement.getAllCourses();
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/department/{departmentId}")
    public ResponseEntity<List<Course>> getCoursesByDepartmentId(@PathVariable Long departmentId){
        List<Course> courses = courseImplement.getCoursesByDepartment(departmentId);
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/faculty/{facultyId}")
    public ResponseEntity<List<Course>> getCoursesByFacultyId(@PathVariable Long facultyId){
        List<Course> courses = courseImplement.getCoursesByFaculty(facultyId);
        return ResponseEntity.ok(courses);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long id) {
        boolean result = courseImplement.deleteCourse(id);
        if (result) {
            return ResponseEntity.ok("Course deleted successfully");
        } else {
            return ResponseEntity.status(400).body("Course deletion failed");
        }
    }

}