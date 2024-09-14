package com.College_directory.Springboot_first_app.repository;

import com.College_directory.Springboot_first_app.model.StudentProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentProfileRepository extends JpaRepository<StudentProfile, Long> {

    // Find StudentProfiles by departmentId and year
    List<StudentProfile> findByDepartment_IdAndYear(Long departmentId, String year);

    StudentProfile findByUserId(Long userId);

    List<StudentProfile> findByDepartmentIdAndYear(long l, String year);

    List<StudentProfile> findByDepartmentId(Long departmentId);

    List<StudentProfile> findStudentsByCourseId(Long courseId);
}
