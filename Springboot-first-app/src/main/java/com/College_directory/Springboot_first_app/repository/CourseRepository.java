package com.College_directory.Springboot_first_app.repository;

import com.College_directory.Springboot_first_app.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
