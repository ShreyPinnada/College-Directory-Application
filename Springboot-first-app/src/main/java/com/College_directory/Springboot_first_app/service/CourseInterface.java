package com.College_directory.Springboot_first_app.service;

import com.College_directory.Springboot_first_app.dto.CourseDTO;
import com.College_directory.Springboot_first_app.model.Course;
import com.College_directory.Springboot_first_app.model.StudentProfile;

import java.util.List;

public interface CourseInterface {
    // Create
    Course createCourse(CourseDTO courseDTO);

    // Read
    Course getCourseById(Long id);
    List<Course> getAllCourses();
    List<Course> getCoursesByDepartment(Long departmentId);
    List<Course> getCoursesByFaculty(Long facultyId);

    // Update
    Course updateCourse(Long id, CourseDTO courseDTO);

    // Delete
    boolean deleteCourse(Long id);

    // Other
    List<StudentProfile> getEnrolledStudents(Long courseId);
}
