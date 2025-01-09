package com.College_directory.Springboot_first_app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.College_directory.Springboot_first_app.model.Department;
import com.College_directory.Springboot_first_app.model.StudentProfile;
import com.College_directory.Springboot_first_app.model.User;

@Repository
public interface StudentProfileRepository extends JpaRepository<StudentProfile, Long> {
    StudentProfile getByUserId(Long userId);

    StudentProfile getByUser(User user);

    List<StudentProfile> findByDepartmentAndYear(Department department, String year);

}
