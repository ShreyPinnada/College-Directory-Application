package com.College_directory.Springboot_first_app.service.implement;

import com.College_directory.Springboot_first_app.dto.StudentProfileDTO;
import com.College_directory.Springboot_first_app.model.Course;
import com.College_directory.Springboot_first_app.model.FacultyProfile;
import com.College_directory.Springboot_first_app.model.StudentProfile;
import com.College_directory.Springboot_first_app.repository.CourseRepository;
import com.College_directory.Springboot_first_app.repository.FacultyProfileRepository;
import com.College_directory.Springboot_first_app.repository.StudentProfileRepository;
import com.College_directory.Springboot_first_app.service.StudentProfileInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImplement implements StudentProfileInterface {

    @Autowired
    private StudentProfileRepository studentProfileRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private FacultyProfileRepository facultyProfileRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public StudentProfile createStudentProfile(Long userId, StudentProfileDTO studentProfileDTO) {
        StudentProfile studentProfile = modelMapper.map(studentProfileDTO, StudentProfile.class);
        studentProfile.setUserId(userId); // Manually set userId
        return studentProfileRepository.save(studentProfile);
    }

    @Override
    public StudentProfile getStudentProfileById(Long userId) {
        return studentProfileRepository.findByUserId(userId);
    }

    @Override
    public List<StudentProfile> getAllStudentProfiles() {
        return studentProfileRepository.findAll();
    }

    @Override
    public List<StudentProfile> searchStudents(String query, String department, String year) {
        return studentProfileRepository.findByDepartmentIdAndYear(Long.parseLong(department), year);
    }

    @Override
    public StudentProfile updateStudentProfile(Long userId, StudentProfileDTO studentProfileDTO) {
        StudentProfile studentProfile = getStudentProfileById(userId);
        if (studentProfile != null) {
            modelMapper.map(studentProfileDTO, studentProfile); // Update existing studentProfile with DTO values
            return studentProfileRepository.save(studentProfile);
        }
        return null;
    }

    @Override
    public StudentProfile updateStudentPhoto(Long userId, String photoUrl) {
        StudentProfile studentProfile = getStudentProfileById(userId);
        if (studentProfile != null) {
            studentProfile.setPhoto(photoUrl);
            return studentProfileRepository.save(studentProfile);
        }
        return null;
    }

    @Override
    public boolean deleteStudentProfile(Long userId) {
        StudentProfile studentProfile = getStudentProfileById(userId);
        if (studentProfile != null) {
            studentProfileRepository.delete(studentProfile);
            return true;
        }
        return false;
    }

    @Override
    public List<Course> getStudentCourses(Long userId) {
        return courseRepository.findCoursesByStudentId(userId);
    }

    @Override
    public List<FacultyProfile> getStudentAdvisors(Long userId) {
        return facultyProfileRepository.findAdvisorsByStudentId(userId);
    }
}
