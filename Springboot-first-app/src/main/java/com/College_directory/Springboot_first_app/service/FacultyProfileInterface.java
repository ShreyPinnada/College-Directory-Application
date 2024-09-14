package com.College_directory.Springboot_first_app.service;

import com.College_directory.Springboot_first_app.dto.FacultyProfileDTO;
import com.College_directory.Springboot_first_app.model.Course;
import com.College_directory.Springboot_first_app.model.FacultyProfile;

import java.util.List;

public interface FacultyProfileInterface {
    // Create
    FacultyProfile createFacultyProfile(Long userId, FacultyProfileDTO facultyProfileDTO);

    // Read
    FacultyProfile getFacultyProfileById(Long userId);
    List<FacultyProfile> getAllFacultyProfiles();

    // Update
    FacultyProfile updateFacultyProfile(Long userId, FacultyProfileDTO facultyProfileDTO);
    FacultyProfile updateFacultyPhoto(Long userId, String photoUrl);

    // Delete
    boolean deleteFacultyProfile(Long userId);

    // Other
    List<Course> getFacultyClasses(Long userId);
    FacultyProfile updateOfficeHours(Long userId, String officeHours);
}
