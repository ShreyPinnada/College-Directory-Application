package com.College_directory.Springboot_first_app.repository;

import com.College_directory.Springboot_first_app.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
}
