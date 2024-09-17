package com.College_directory.Springboot_first_app.service.implement;

import com.College_directory.Springboot_first_app.dto.user.AdministratorProfileDTO;
import com.College_directory.Springboot_first_app.model.AdministratorProfile;
import com.College_directory.Springboot_first_app.model.Department;
import com.College_directory.Springboot_first_app.model.User;
import com.College_directory.Springboot_first_app.repository.AdministratorProfileRepository;
import com.College_directory.Springboot_first_app.repository.DepartmentRepository;
import com.College_directory.Springboot_first_app.repository.UserRepository;
import com.College_directory.Springboot_first_app.service.AdministratorProfileInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministratorServiceImplement implements AdministratorProfileInterface {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdministratorProfileRepository administratorProfileRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public AdministratorProfile createAdministratorProfile(Long userId, AdministratorProfileDTO administratorProfileDTO) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
        AdministratorProfile existingAdministrator = administratorProfileRepository.getByUser(existingUser);
        if(existingAdministrator != null){
            throw new IllegalArgumentException("Administrator with id " + userId + " already exists");
        }
        Department department = departmentRepository.findById(administratorProfileDTO.getDepartmentId())
                .orElseThrow(() -> new IllegalArgumentException("Department with id " + administratorProfileDTO.getDepartmentId() + " does not exist"));

        AdministratorProfile newAdministrator = new AdministratorProfile();
        newAdministrator.setUser(existingUser);
        newAdministrator.setPhoto(administratorProfileDTO.getPhoto());
        newAdministrator.setDepartment(department);
        return administratorProfileRepository.save(newAdministrator);
    }

    @Override
    public AdministratorProfile getAdministratorProfileById(Long userId) {
        return administratorProfileRepository.getByUserId(userId);
    }

    @Override
    public List<AdministratorProfile> getAllAdministratorProfiles() {
        return administratorProfileRepository.findAll();
    }

    @Override
    public AdministratorProfile updateAdministratorProfile(Long userId, AdministratorProfileDTO administratorProfileDTO) {
        AdministratorProfile existingAdministratorProfile = administratorProfileRepository.getByUserId(userId);
        if(existingAdministratorProfile == null){
            throw new IllegalArgumentException("Administrator with id " + userId + " does not exist");
        }

        if(administratorProfileDTO.getPhoto() != null){
            existingAdministratorProfile.setPhoto(administratorProfileDTO.getPhoto());
        }
        if(administratorProfileDTO.getDepartmentId() != null){
            Department department = departmentRepository.findById(administratorProfileDTO.getDepartmentId())
                    .orElseThrow(() -> new IllegalArgumentException("Department with id " + administratorProfileDTO.getDepartmentId() + " does not exist"));
            existingAdministratorProfile.setDepartment(department);
        }
        return administratorProfileRepository.save(existingAdministratorProfile);
    }

    @Override
    public boolean deleteAdministratorProfile(Long userId) {
        AdministratorProfile administratorProfile = administratorProfileRepository.getById(userId);
        administratorProfileRepository.delete(administratorProfile);
        return false;
    }
}
