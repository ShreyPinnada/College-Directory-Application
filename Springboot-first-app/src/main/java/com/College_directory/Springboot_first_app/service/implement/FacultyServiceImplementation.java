package com.College_directory.Springboot_first_app.service.implement;

import com.College_directory.Springboot_first_app.dto.user.FacultyCreateProfileDTO;
import com.College_directory.Springboot_first_app.dto.user.FacultyProfileDTO;
import com.College_directory.Springboot_first_app.model.Department;
import com.College_directory.Springboot_first_app.model.FacultyProfile;
import com.College_directory.Springboot_first_app.model.User;
import com.College_directory.Springboot_first_app.repository.DepartmentRepository;
import com.College_directory.Springboot_first_app.repository.FacultyProfileRepository;
import com.College_directory.Springboot_first_app.repository.UserRepository;
import com.College_directory.Springboot_first_app.service.FacultyProfileServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyServiceImplementation implements FacultyProfileServiceInterface {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FacultyProfileRepository facultyProfileRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public FacultyProfile createFacultyProfile(Long userId, FacultyCreateProfileDTO facultyCreateProfileDTO) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
        FacultyProfile existingFaculty = facultyProfileRepository.getByUser(existingUser);
        if(existingFaculty != null){
            throw new IllegalArgumentException("Faculty with id " + userId + " already exists");
        }
        Department department = departmentRepository.findById(facultyCreateProfileDTO.getDepartmentId())
                .orElseThrow(() -> new IllegalArgumentException("Department with id " + facultyCreateProfileDTO.getDepartmentId() + " does not exist"));

        FacultyProfile newFaculty = new FacultyProfile();
        newFaculty.setUser(existingUser);
        newFaculty.setPhoto(facultyCreateProfileDTO.getPhoto());
        newFaculty.setDepartment(department);
        newFaculty.setOfficeHours(facultyCreateProfileDTO.getOfficeHours());
        return facultyProfileRepository.save(newFaculty);
    }

    @Override
    public FacultyProfile getFacultyProfileById(Long userId) {
        return facultyProfileRepository.getByUserId(userId);
    }

    @Override
    public List<FacultyProfile> getAllFacultyProfiles() {
        return facultyProfileRepository.findAll();
    }

    @Override
    public FacultyProfile updateFacultyProfile(Long userId, FacultyProfileDTO facultyProfileDTO) {
        FacultyProfile existingFacultyProfile = facultyProfileRepository.getByUserId(userId);
        if(existingFacultyProfile == null){
            throw new IllegalArgumentException("Faculty with id " + userId + " does not exist");
        }

        if(facultyProfileDTO.getPhoto() != null){
            existingFacultyProfile.setPhoto(facultyProfileDTO.getPhoto());
        }
        if(facultyProfileDTO.getDepartmentId() != null){
            Department department = departmentRepository.findById(facultyProfileDTO.getDepartmentId())
                    .orElseThrow(() -> new IllegalArgumentException("Department with id " + facultyProfileDTO.getDepartmentId() + " does not exist"));
            existingFacultyProfile.setDepartment(department);
        }
        if(facultyProfileDTO.getOfficeHours() != null){
            existingFacultyProfile.setOfficeHours(facultyProfileDTO.getOfficeHours());
        }
        return facultyProfileRepository.save(existingFacultyProfile);
    }

    @Override
    public boolean deleteFacultyProfile(Long userId) {
        FacultyProfile facultyProfile = facultyProfileRepository.getById(userId);
        facultyProfileRepository.delete(facultyProfile);
        return true;
    }
}
