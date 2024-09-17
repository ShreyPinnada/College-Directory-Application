package com.College_directory.Springboot_first_app.service;

import com.College_directory.Springboot_first_app.dto.user.AdministratorProfileDTO;
import com.College_directory.Springboot_first_app.model.AdministratorProfile;

import java.util.List;

public interface AdministratorProfileInterface {
    // Create
    AdministratorProfile createAdministratorProfile(Long userId, AdministratorProfileDTO administratorProfileDTO);

    // Read
    AdministratorProfile getAdministratorProfileById(Long userId);
    List<AdministratorProfile> getAllAdministratorProfiles();

    // Update
    AdministratorProfile updateAdministratorProfile(Long userId, AdministratorProfileDTO administratorProfileDTO);
    // Delete
    boolean deleteAdministratorProfile(Long userId);
}
