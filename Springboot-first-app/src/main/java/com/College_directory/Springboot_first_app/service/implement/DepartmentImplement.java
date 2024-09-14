package com.College_directory.Springboot_first_app.service.implement;

import com.College_directory.Springboot_first_app.dto.DepartmentDTO;
import com.College_directory.Springboot_first_app.model.Course;
import com.College_directory.Springboot_first_app.model.Department;
import com.College_directory.Springboot_first_app.model.FacultyProfile;
import com.College_directory.Springboot_first_app.model.StudentProfile;
import com.College_directory.Springboot_first_app.repository.DepartmentRepository;
import com.College_directory.Springboot_first_app.repository.StudentProfileRepository;
import com.College_directory.Springboot_first_app.repository.FacultyProfileRepository;
import com.College_directory.Springboot_first_app.repository.CourseRepository;
import com.College_directory.Springboot_first_app.service.DepartmentInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentImplement implements DepartmentInterface {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private StudentProfileRepository studentProfileRepository;

    @Autowired
    private FacultyProfileRepository facultyProfileRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Department createDepartment(DepartmentDTO departmentDTO) {
        Department department = modelMapper.map(departmentDTO, Department.class);
        return departmentRepository.save(department);
    }

    @Override
    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department updateDepartment(Long id, DepartmentDTO departmentDTO) {
        Department department = getDepartmentById(id);
        if (department != null) {
            modelMapper.map(departmentDTO, department);
            return departmentRepository.save(department);
        }
        return null;
    }

    @Override
    public boolean deleteDepartment(Long id) {
        if (departmentRepository.existsById(id)) {
            departmentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<StudentProfile> getDepartmentStudents(Long departmentId) {
        return studentProfileRepository.findByDepartmentId(departmentId);
    }

    @Override
    public List<FacultyProfile> getDepartmentFaculty(Long departmentId) {
        return facultyProfileRepository.findByDepartmentId(departmentId);
    }

    @Override
    public List<Course> getDepartmentCourses(Long departmentId) {
        return courseRepository.findByDepartmentId(departmentId);
    }
}