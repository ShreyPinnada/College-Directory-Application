package com.College_directory.Springboot_first_app.repository;

import com.College_directory.Springboot_first_app.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findCoursesByStudentId(Long userId);

    List<Course> findByDepartmentId(Long departmentId);

    List<Course> findByFacultyId(Long facultyId);
}
