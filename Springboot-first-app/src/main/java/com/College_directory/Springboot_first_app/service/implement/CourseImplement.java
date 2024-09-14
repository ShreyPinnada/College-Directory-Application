package com.College_directory.Springboot_first_app.service.implement;

import com.College_directory.Springboot_first_app.dto.CourseDTO;
import com.College_directory.Springboot_first_app.model.Course;
import com.College_directory.Springboot_first_app.model.StudentProfile;
import com.College_directory.Springboot_first_app.repository.CourseRepository;
import com.College_directory.Springboot_first_app.repository.StudentProfileRepository;
import com.College_directory.Springboot_first_app.service.CourseInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseImplement implements CourseInterface {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentProfileRepository studentProfileRepository;

    @Override
    public Course createCourse(CourseDTO courseDTO) {
        Course course = new Course();
        course.setTitle(courseDTO.getTitle());
        course.setDescription(courseDTO.getDescription());
        course.setDepartmentId(courseDTO.getDepartmentId());
        course.setFacultyId(courseDTO.getFacultyId());
        return courseRepository.save(course);
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public List<Course> getCoursesByDepartment(Long departmentId) {
        return courseRepository.findByDepartmentId(departmentId);
    }

    @Override
    public List<Course> getCoursesByFaculty(Long facultyId) {
        return courseRepository.findByFacultyId(facultyId);
    }

    @Override
    public Course updateCourse(Long id, CourseDTO courseDTO) {
        Course course = getCourseById(id);
        if (course != null) {
            course.setTitle(courseDTO.getTitle());
            course.setDescription(courseDTO.getDescription());
            course.setDepartmentId(courseDTO.getDepartmentId());
            course.setFacultyId(courseDTO.getFacultyId());
            return courseRepository.save(course);
        }
        return null;
    }

    @Override
    public boolean deleteCourse(Long id) {
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<StudentProfile> getEnrolledStudents(Long courseId) {
        // Assuming that StudentProfileRepository has a method for fetching students by course ID
        return studentProfileRepository.findStudentsByCourseId(courseId);
    }
}