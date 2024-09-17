package com.College_directory.Springboot_first_app.service.implement;


import com.College_directory.Springboot_first_app.dto.course.CourseCreateDTO;
import com.College_directory.Springboot_first_app.dto.course.CourseUpdateDTO;
import com.College_directory.Springboot_first_app.model.Course;
import com.College_directory.Springboot_first_app.model.Department;
import com.College_directory.Springboot_first_app.model.FacultyProfile;
import com.College_directory.Springboot_first_app.model.User;
import com.College_directory.Springboot_first_app.repository.CourseRepository;
import com.College_directory.Springboot_first_app.repository.DepartmentRepository;
import com.College_directory.Springboot_first_app.repository.FacultyProfileRepository;
import com.College_directory.Springboot_first_app.repository.UserRepository;
import com.College_directory.Springboot_first_app.service.CourseInterface;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseImplement implements CourseInterface {

    private final CourseRepository courseRepository;
    private final DepartmentRepository departmentRepository;
    private final FacultyProfileRepository facultyProfileRepository;
    private final UserRepository userRepository;

    public CourseImplement(CourseRepository courseRepository, DepartmentRepository departmentRepository, FacultyProfileRepository facultyProfileRepository, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.departmentRepository = departmentRepository;
        this.facultyProfileRepository = facultyProfileRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Course createCourse(CourseCreateDTO courseCreateDTO) {
        Optional<Course> existingCourse = courseRepository.findByTitle(courseCreateDTO.getTitle());
        if(existingCourse.isPresent()){
            throw new IllegalArgumentException("Course with title " + courseCreateDTO.getTitle() + " already exists");
        }
        Department department = departmentRepository.findById(courseCreateDTO.getDepartmentId())
                .orElseThrow(() -> new IllegalArgumentException("Department not found with id: " + courseCreateDTO.getDepartmentId()));

        User existingUser = userRepository.findById(courseCreateDTO.getFacultyId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + courseCreateDTO.getFacultyId()));
        FacultyProfile existingFaculty = facultyProfileRepository.getByUser(existingUser);
        if (existingFaculty == null) {
            throw new IllegalArgumentException("Faculty profile not found for user id: " + courseCreateDTO.getFacultyId());
        }
        Course newCourse = new Course();
        newCourse.setTitle(courseCreateDTO.getTitle());
        newCourse.setDescription(courseCreateDTO.getDescription());
        newCourse.setDepartment(department);
        newCourse.setFaculty(existingFaculty);
        return courseRepository.save(newCourse);
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
        return courseRepository.findAllByDepartmentId(departmentId);
    }

    @Override
    public List<Course> getCoursesByFaculty(Long facultyId) {
       return courseRepository.findAllByFaculty_UserId(facultyId);
    }

    @Override
    public Course updateCourse(Long id, CourseUpdateDTO courseUpdateDTO) {
        Course existingCourse = courseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Course not found with id: " + id));
        existingCourse.setTitle(courseUpdateDTO.getTitle());
        existingCourse.setDescription(courseUpdateDTO.getDescription());

        Department department = departmentRepository.findById(courseUpdateDTO.getDepartmentId())
                .orElseThrow(() -> new IllegalArgumentException("Department not found with id: " + courseUpdateDTO.getDepartmentId()));
        existingCourse.setDepartment(department);

        User facultyUser = userRepository.findById(courseUpdateDTO.getFacultyId())
                .orElseThrow(() -> new IllegalArgumentException("Faculty not found with id: " + courseUpdateDTO.getFacultyId()));
        FacultyProfile facultyProfile = facultyProfileRepository.getByUser(facultyUser);
        existingCourse.setFaculty(facultyProfile);
        return courseRepository.save(existingCourse);
    }

    @Override
    public boolean deleteCourse(Long id) {
        return false;
    }
}

