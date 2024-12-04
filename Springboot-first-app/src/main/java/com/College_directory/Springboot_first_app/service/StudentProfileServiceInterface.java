package com.College_directory.Springboot_first_app.service;

import com.College_directory.Springboot_first_app.dto.user.FacultyProfileDTO;
import com.College_directory.Springboot_first_app.dto.user.StudentProfileDTO;
import com.College_directory.Springboot_first_app.model.FacultyProfile;
import com.College_directory.Springboot_first_app.model.StudentProfile;

import java.util.List;

public interface StudentProfileServiceInterface {

    //CREATE
    StudentProfile createStudentProfile(Long userId, StudentProfileDTO studentProfileDTO);

    //READ
    StudentProfile getStudentProfileById(Long userId);
    List<StudentProfile> getAllStudents();
    List<StudentProfile> searchStudents(Long departmentId, String year);

    //UPDATE
    StudentProfile updateStudentProfile(Long userId, StudentProfileDTO studentProfileDTO);

    //DELETE
    boolean deleteStudentProfile(Long userId);

//    // Create
//    StudentProfile createorUpdateStudentProfile(StudentProfileDTO studentProfileDTO);
//
//    // Read
//    StudentProfile getStudentProfileById(Long userId);
//    List<StudentProfile> getAllStudents();
//    List<StudentProfile> searchStudents(Long departmentId, String year);
//
//    // Update
//    StudentProfile updateStudentProfile(Long userId, StudentProfileDTO studentProfileDTO);
//
//    // Delete
//    boolean deleteStudentProfile(Long userId);
//
//    // Other
//    //List<Course> getStudentCourses(Long userId);
//    List<FacultyProfile> getStudentAdvisors(Long userId);
       List<FacultyProfileDTO> getFacultiesByStudentId(Long studentId);
}
