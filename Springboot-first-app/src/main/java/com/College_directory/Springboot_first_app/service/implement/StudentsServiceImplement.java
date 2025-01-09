package com.College_directory.Springboot_first_app.service.implement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.College_directory.Springboot_first_app.dto.user.FacultyProfileDTO;
import com.College_directory.Springboot_first_app.dto.user.StudentProfileDTO;
import com.College_directory.Springboot_first_app.model.Department;
import com.College_directory.Springboot_first_app.model.Enrollment;
import com.College_directory.Springboot_first_app.model.StudentProfile;
import com.College_directory.Springboot_first_app.model.User;
import com.College_directory.Springboot_first_app.repository.DepartmentRepository;
import com.College_directory.Springboot_first_app.repository.EnrollmentRepository;
import com.College_directory.Springboot_first_app.repository.FacultyProfileRepository;
import com.College_directory.Springboot_first_app.repository.StudentProfileRepository;
import com.College_directory.Springboot_first_app.repository.UserRepository;
import com.College_directory.Springboot_first_app.service.StudentProfileServiceInterface;

@Service
public class StudentsServiceImplement implements StudentProfileServiceInterface {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private StudentProfileRepository studentProfileRepository;

    @Autowired
    private FacultyProfileRepository facultyProfileRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Override
    public StudentProfile createStudentProfile(Long userId, StudentProfileDTO studentProfileDTO) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        StudentProfile existedStudent = studentProfileRepository.getByUser(existingUser);
        if (existedStudent != null) {
            throw new IllegalArgumentException("Student with id " + userId + " already exists");
        }
        Department department = departmentRepository.findById(studentProfileDTO.getDepartmentId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Department with id " + studentProfileDTO.getDepartmentId() + " does not exist"));
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
        Department department = departmentRepository.findById(departmentId).orElse(null);
        return studentProfileRepository.findByDepartmentAndYear(department, year);
    }

    @Override
    public StudentProfile updateStudentProfile(Long userId, StudentProfileDTO studentProfileDTO) {
        StudentProfile existingStudentProfile = studentProfileRepository.getByUserId(userId);
        if (existingStudentProfile == null) {
            throw new IllegalArgumentException("Student with id " + userId + " does not exist");
        }

        if (studentProfileDTO.getPhoto() != null) {
            existingStudentProfile.setPhoto(studentProfileDTO.getPhoto());
        }
        if (studentProfileDTO.getDepartmentId() != null) {
            Department department = departmentRepository.findById(studentProfileDTO.getDepartmentId())
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Department with id " + studentProfileDTO.getDepartmentId() + " does not exist"));
            existingStudentProfile.setDepartment(department);
        }
        if (studentProfileDTO.getYear() != null) {
            existingStudentProfile.setYear(studentProfileDTO.getYear());
        }
        return studentProfileRepository.save(existingStudentProfile);
    }

    @Override
    public boolean deleteStudentProfile(Long userId) {
        List<Enrollment> enrollments = enrollmentRepository.findAllByStudent_UserId(userId);
        if (!enrollments.isEmpty()) {
            enrollmentRepository.deleteAll(enrollments);
        }

        // Delete the student profile
        if (studentProfileRepository.existsById(userId)) {
            studentProfileRepository.deleteById(userId);
        }

        // Delete the user from the appuser table
        userRepository.deleteById(userId);

        return true;
    }

    @Override
    public List<FacultyProfileDTO> getFacultiesByStudentId(Long studentId) {
        List<Object[]> results = facultyProfileRepository.findFacultiesByStudentId(studentId);

        // Convert Object[] to DTO
        List<FacultyProfileDTO> facultyProfileDTOs = new ArrayList<>();
        for (Object[] result : results) {
            FacultyProfileDTO dto = new FacultyProfileDTO();
            dto.setId((Long) result[0]); // user_id
            dto.setUsername((String) result[1]); // username
            dto.setFacultyName((String) result[2]); // faculty_name
            dto.setEmail((String) result[3]); // email
            dto.setPhone((String) result[4]); // phone
            dto.setDepartmentId((Long) result[5]); // department_id
            dto.setDepartmentName((String) result[6]); // department_name
            dto.setOfficeHours((String) result[7]); // office_hours
            facultyProfileDTOs.add(dto);
        }
        return facultyProfileDTOs;
    }
}