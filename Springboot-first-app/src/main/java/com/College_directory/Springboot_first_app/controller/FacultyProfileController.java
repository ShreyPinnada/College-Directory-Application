package com.College_directory.Springboot_first_app.controller;

import com.College_directory.Springboot_first_app.dto.user.FacultyCreateProfileDTO;
import com.College_directory.Springboot_first_app.dto.user.FacultyProfileDTO;
import com.College_directory.Springboot_first_app.model.FacultyProfile;
import com.College_directory.Springboot_first_app.service.FacultyProfileServiceInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/faculty")
public class FacultyProfileController {

    private final FacultyProfileServiceInterface facultyProfileService;

    public FacultyProfileController(FacultyProfileServiceInterface facultyProfileService) {
        this.facultyProfileService = facultyProfileService;
    }

    @PostMapping("/create/{userId}")
    public ResponseEntity<FacultyProfile> createFacultyProfile(@PathVariable Long userId, @RequestBody FacultyCreateProfileDTO facultyCreateProfileDTO) {
        FacultyProfile facultyProfile = facultyProfileService.createFacultyProfile(userId, facultyCreateProfileDTO);
        return ResponseEntity.ok(facultyProfile);
    }

    @GetMapping("{userId}")
    public ResponseEntity<FacultyProfile> getFacultyProfileByUserId(@PathVariable Long userId) {
        FacultyProfile faculty = facultyProfileService.getFacultyProfileById(userId);
        return ResponseEntity.ok(faculty);
    }

    @GetMapping
    public ResponseEntity<List<FacultyProfile>> getAllFacultyProfiles() {
        List<FacultyProfile> faculties = facultyProfileService.getAllFacultyProfiles();
        return ResponseEntity.ok(faculties);
    }

    @PatchMapping("{userId}")
    public ResponseEntity<FacultyProfile> updateFacultyProfile(@PathVariable Long userId, @RequestBody FacultyProfileDTO facultyProfileDTO) {
        FacultyProfile facultyProfile = facultyProfileService.updateFacultyProfile(userId, facultyProfileDTO);
        return ResponseEntity.ok(facultyProfile);
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<String> deleteFacultyProfile(@PathVariable Long userId) {
        boolean result = facultyProfileService.deleteFacultyProfile(userId);
        if (result) {
            return ResponseEntity.ok("Faculty deleted successfully");
        } else {
            return ResponseEntity.status(400).body("Faculty deletion failed");
        }
    }

}
