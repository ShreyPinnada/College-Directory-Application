package com.College_directory.Springboot_first_app.service;

import com.College_directory.Springboot_first_app.dto.StudentProfileDTO;
import com.College_directory.Springboot_first_app.model.Course;
import com.College_directory.Springboot_first_app.model.FacultyProfile;
import com.College_directory.Springboot_first_app.model.StudentProfile;
import java.util.List;
public interface StudentProfileInterface {
    // Create
    StudentProfile createStudentProfile(Long userId, StudentProfileDTO studentProfileDTO);

    // Read
    StudentProfile getStudentProfileById(Long userId);
    List<StudentProfile> getAllStudentProfiles();
    List<StudentProfile> searchStudents(String query, String department, String year);

    // Update
    StudentProfile updateStudentProfile(Long userId, StudentProfileDTO studentProfileDTO);
    StudentProfile updateStudentPhoto(Long userId, String photoUrl);

    // Delete
    boolean deleteStudentProfile(Long userId);

    // Other
    List<Course> getStudentCourses(Long userId);
    List<FacultyProfile> getStudentAdvisors(Long userId);
}
