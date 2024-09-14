package com.College_directory.Springboot_first_app.repository;


import com.College_directory.Springboot_first_app.model.StudentProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentProfileRepository extends JpaRepository<StudentProfile, Long> {
}