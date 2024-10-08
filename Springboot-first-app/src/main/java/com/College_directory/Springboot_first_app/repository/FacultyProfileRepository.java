package com.College_directory.Springboot_first_app.repository;


import com.College_directory.Springboot_first_app.model.FacultyProfile;
import com.College_directory.Springboot_first_app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacultyProfileRepository extends JpaRepository<FacultyProfile, Long> {
    FacultyProfile getByUser(User existingUser);

    FacultyProfile getByUserId(Long userId);

    @Query(value = "SELECT DISTINCT f.user_id, au.username, au.name AS faculty_name, au.email, au.phone, " +
            "f.department_id, d.name AS department_name, f.office_hours " +
            "FROM enrollment e " +
            "JOIN course c ON e.course_id = c.id " +
            "JOIN facultyprofile f ON c.faculty_id = f.user_id " +
            "JOIN appuser au ON f.user_id = au.id " +
            "JOIN department d ON f.department_id = d.id " +
            "WHERE e.student_id = :studentId", nativeQuery = true)
    List<Object[]> findFacultiesByStudentId(@Param("studentId") Long studentId);
}

