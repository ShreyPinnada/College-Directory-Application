package com.College_directory.Springboot_first_app.service;

import java.util.List;

import com.College_directory.Springboot_first_app.dto.course.CourseCreateDTO;
import com.College_directory.Springboot_first_app.dto.course.CourseUpdateDTO;
import com.College_directory.Springboot_first_app.model.Course;

public interface CourseInterface {
    // Create
    Course createCourse(CourseCreateDTO courseCreateDTO);

    // Read
    Course getCourseById(Long id);

    List<Course> getAllCourses();

    List<Course> getCoursesByDepartment(Long departmentId);

    List<Course> getCoursesByFaculty(Long facultyId);

    // Update
    Course updateCourse(Long id, CourseUpdateDTO courseUpdateDTO);

    // Delete
    boolean deleteCourse(Long id);

    // Other
    // List<StudentProfile> getEnrolledStudents(Long courseId);
}
