package com.College_directory.Springboot_first_app.service.implement;

import com.College_directory.Springboot_first_app.dto.enrollment.EnrollmentCreateDTO;
import com.College_directory.Springboot_first_app.model.Course;
import com.College_directory.Springboot_first_app.model.Enrollment;
import com.College_directory.Springboot_first_app.model.StudentProfile;
import com.College_directory.Springboot_first_app.model.User;
import com.College_directory.Springboot_first_app.repository.CourseRepository;
import com.College_directory.Springboot_first_app.repository.EnrollmentRepository;
import com.College_directory.Springboot_first_app.repository.StudentProfileRepository;
import com.College_directory.Springboot_first_app.repository.UserRepository;
import com.College_directory.Springboot_first_app.service.EnrollmentInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentImplement implements EnrollmentInterface {

    @Autowired
    private StudentProfileRepository studentProfileRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EnrollmentRepository enrollmentRepository;
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Enrollment enrollStudent(Long userId, EnrollmentCreateDTO enrollmentCreateDTO) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        StudentProfile existedStudent = studentProfileRepository.getByUser(existingUser);
        if (existedStudent == null) {
            throw new IllegalArgumentException("Student with id " + userId + " does not exist");
        }

        Course existingCourse = courseRepository.findById(enrollmentCreateDTO.getCourseId())
                .orElseThrow(() -> new IllegalArgumentException("Course with id " + enrollmentCreateDTO.getCourseId() + " does not exist"));

        Enrollment newEnrollment = new Enrollment();
        newEnrollment.setStudent(existedStudent);
        newEnrollment.setCourse(existingCourse);

        return enrollmentRepository.save(newEnrollment);
    }

    @Override
    public Enrollment getEnrollmentById(Long id) {
        return enrollmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Enrollment not found with id: " + id));
    }

    @Override
    public List<Enrollment> getEnrollmentsByStudent(Long studentId) {
        return enrollmentRepository.findAllByStudent_UserId(studentId);
    }

    @Override
    public List<Enrollment> getEnrollmentsByCourse(Long courseId) {
        return enrollmentRepository.findAllByCourse_Id(courseId);
    }

    @Override
    public boolean unEnrollStudent(Long studentId, Long courseId) {
        Optional<Enrollment> enrollmentOptional = enrollmentRepository.findByStudent_UserIdAndCourse_Id(studentId, courseId);
        if (enrollmentOptional.isPresent()) {
            enrollmentRepository.delete(enrollmentOptional.get());
            return true;
        } else {
            return false;
        }
    }
}