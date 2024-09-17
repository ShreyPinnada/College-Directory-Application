package com.College_directory.Springboot_first_app.service.implement;

import com.College_directory.Springboot_first_app.dto.user.StudentProfileDTO;
import com.College_directory.Springboot_first_app.model.Department;
import com.College_directory.Springboot_first_app.model.StudentProfile;
import com.College_directory.Springboot_first_app.model.User;
import com.College_directory.Springboot_first_app.repository.DepartmentRepository;
import com.College_directory.Springboot_first_app.repository.StudentProfileRepository;
import com.College_directory.Springboot_first_app.repository.UserRepository;
import com.College_directory.Springboot_first_app.service.StudentProfileServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentsServiceImplement implements StudentProfileServiceInterface {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private StudentProfileRepository studentProfileRepository;

    @Override
    public StudentProfile createStudentProfile(Long userId, StudentProfileDTO studentProfileDTO) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        StudentProfile existedStudent = studentProfileRepository.getByUser(existingUser);
        if(existedStudent != null){
            throw new IllegalArgumentException("Student with id " + userId + " already exists");
        }
        Department department = departmentRepository.findById(studentProfileDTO.getDepartmentId())
                .orElseThrow(() -> new IllegalArgumentException("Department with id " + studentProfileDTO.getDepartmentId() + " does not exist"));
        StudentProfile newStudent = new StudentProfile();
        newStudent.setUser(existingUser);
        newStudent.setPhoto(studentProfileDTO.getPhoto());
        newStudent.setDepartment(department);
        newStudent.setYear(studentProfileDTO.getYear());
        return studentProfileRepository.save(newStudent);
    }

    @Override
    public StudentProfile getStudentProfileById(Long userId) {
        return studentProfileRepository.getByUserId(userId);
    }

    @Override
    public List<StudentProfile> getAllStudents() {
        return studentProfileRepository.findAll();
    }

    @Override
    public List<StudentProfile> searchStudents(Long departmentId, String year) {
        return studentProfileRepository.findByDepartmentIdAndYear(departmentId, year);
    }

    @Override
    public StudentProfile updateStudentProfile(Long userId, StudentProfileDTO studentProfileDTO) {
        StudentProfile existingStudentProfile = studentProfileRepository.getByUserId(userId);
        if(existingStudentProfile == null){
            throw new IllegalArgumentException("Student with id " + userId + " does not exist");
        }

        if (studentProfileDTO.getPhoto() != null) {
            existingStudentProfile.setPhoto(studentProfileDTO.getPhoto());
        }
        if (studentProfileDTO.getDepartmentId() != null) {
            Department department = departmentRepository.findById(studentProfileDTO.getDepartmentId())
                    .orElseThrow(() -> new IllegalArgumentException("Department with id " + studentProfileDTO.getDepartmentId() + " does not exist"));
            existingStudentProfile.setDepartment(department);
        }
        if (studentProfileDTO.getYear() != null) {
            existingStudentProfile.setYear(studentProfileDTO.getYear());
        }
        return studentProfileRepository.save(existingStudentProfile);
    }

    @Override
    public boolean deleteStudentProfile(Long userId) {
        StudentProfile studentProfile = studentProfileRepository.getById(userId);
        studentProfileRepository.delete(studentProfile);
        return true;
    }
}
