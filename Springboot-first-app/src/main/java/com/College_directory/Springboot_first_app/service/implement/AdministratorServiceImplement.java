package com.College_directory.Springboot_first_app.service.implement;

import com.College_directory.Springboot_first_app.dto.AdministratorProfileDTO;
import com.College_directory.Springboot_first_app.model.AdministratorProfile;
import com.College_directory.Springboot_first_app.repository.AdministratorProfileRepository;
import com.College_directory.Springboot_first_app.service.AdministratorProfileInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministratorServiceImplement implements AdministratorProfileInterface {
    @Autowired
    private AdministratorProfileRepository administratorProfileRepository;

    @Override
    public AdministratorProfile createAdministratorProfile(Long userId, AdministratorProfileDTO administratorProfileDTO) {
        AdministratorProfile administratorProfile = new AdministratorProfile();
        administratorProfile.setUserId(userId);
        administratorProfile.setPhoto(administratorProfileDTO.getPhoto());
        administratorProfile.setDepartment(administratorProfileDTO.getDepartmentId());
        return administratorProfileRepository.save(administratorProfile);
    }

    @Override
    public AdministratorProfile getAdministratorProfileById(Long userId) {
        return administratorProfileRepository.findByUserId(userId);
    }

    @Override
    public List<AdministratorProfile> getAllAdministratorProfiles() {
        return administratorProfileRepository.findAll();
    }

    @Override
    public AdministratorProfile updateAdministratorProfile(Long userId, AdministratorProfileDTO administratorProfileDTO) {
        AdministratorProfile administratorProfile = getAdministratorProfileById(userId);
        if (administratorProfile != null) {
            administratorProfile.setPhoto(administratorProfileDTO.getPhoto());
            administratorProfile.setDepartmentId(administratorProfileDTO.getDepartmentId());
            return administratorProfileRepository.save(administratorProfile);
        }
        return null;
    }

    @Override
    public AdministratorProfile updateAdministratorPhoto(Long userId, String photoUrl) {
        AdministratorProfile administratorProfile = getAdministratorProfileById(userId);
        if (administratorProfile != null) {
            administratorProfile.setPhoto(photoUrl);
            return administratorProfileRepository.save(administratorProfile);
        }
        return null;
    }

    @Override
    public boolean deleteAdministratorProfile(Long userId) {
        AdministratorProfile administratorProfile = getAdministratorProfileById(userId);
        if (administratorProfile != null) {
            administratorProfileRepository.delete(administratorProfile);
            return true;
        }
        return false;
    }
}