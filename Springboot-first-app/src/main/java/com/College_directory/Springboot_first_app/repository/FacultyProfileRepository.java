package com.College_directory.Springboot_first_app.repository;

import com.College_directory.Springboot_first_app.model.FacultyProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FacultyProfileRepository extends JpaRepository<FacultyProfile, Long> {
    List<FacultyProfile> findAdvisorsByStudentId(Long userId);

    List<FacultyProfile> findByDepartment_Id(Long userId);

    List<FacultyProfile> findByDepartmentId(Long departmentId);

    List<FacultyProfile> findByDepartment_StudentId(Long studentId);
}
