package com.College_directory.Springboot_first_app.controller;

import com.College_directory.Springboot_first_app.dto.user.AdministratorProfileDTO;
import com.College_directory.Springboot_first_app.model.AdministratorProfile;
import com.College_directory.Springboot_first_app.service.implement.AdministratorServiceImplement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdministratorController {

    private final AdministratorServiceImplement administratorService;

    public AdministratorController(AdministratorServiceImplement administratorService) {
        this.administratorService = administratorService;
    }

    @PostMapping("/create/{userId}")
    public ResponseEntity<AdministratorProfile> createAdministratorProfile(@PathVariable Long userId, @RequestBody AdministratorProfileDTO administratorProfileDTO) {
        AdministratorProfile administratorProfile = administratorService.createAdministratorProfile(userId, administratorProfileDTO);
        return ResponseEntity.ok(administratorProfile);
    }

    @GetMapping("{userId}")
    public ResponseEntity<AdministratorProfile> getAdministratorProfileByUserId(@PathVariable Long userId) {
        AdministratorProfile administrator = administratorService.getAdministratorProfileById(userId);
        return ResponseEntity.ok(administrator);
    }

    @GetMapping
    public ResponseEntity<List<AdministratorProfile>> getAllAdministratorProfiles() {
        List<AdministratorProfile> administrators = administratorService.getAllAdministratorProfiles();
        return ResponseEntity.ok(administrators);
    }

    @PutMapping("{userId}")
    public ResponseEntity<AdministratorProfile> updateAdministratorProfile(@PathVariable Long userId, @RequestBody AdministratorProfileDTO administratorProfileDTO) {
        AdministratorProfile administratorProfile = administratorService.updateAdministratorProfile(userId, administratorProfileDTO);
        return ResponseEntity.ok(administratorProfile);
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<String> deleteAdministratorProfile(@PathVariable Long userId) {
        boolean result = administratorService.deleteAdministratorProfile(userId);
        if (result) {
            return ResponseEntity.ok("Administrator deleted successfully");
        } else {
            return ResponseEntity.status(400).body("Administrator deletion failed");
        }
    }
}
