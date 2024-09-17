//package com.College_directory.Springboot_first_app.service.implement;
//
//import com.College_directory.Springboot_first_app.model.Course;
//import com.College_directory.Springboot_first_app.model.Enrollment;
//import com.College_directory.Springboot_first_app.model.StudentProfile;
//import com.College_directory.Springboot_first_app.repository.CourseRepository;
//import com.College_directory.Springboot_first_app.repository.EnrollmentRepository;
//import com.College_directory.Springboot_first_app.repository.StudentProfileRepository;
//import com.College_directory.Springboot_first_app.service.DashboardInterface;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Map;
//import java.util.stream.Collectors;
//
//@Service
//public class DashboardImplement implements DashboardInterface {
//
//    @Autowired
//    private StudentProfileRepository studentRepository;
//
//    @Autowired
//    private CourseRepository courseRepository;
//
//    @Autowired
//    private EnrollmentRepository enrollmentRepository;
//
//    @Override
//    public Map<String, Integer> getStudentEnrollmentTrends() {
//        return enrollmentRepository.findAll().stream()
//                .collect(Collectors.groupingBy(
//                        e -> e.getEnrollmentDate().getMonth().toString(),
//                        Collectors.summingInt(e -> 1)
//                ));
//    }
//
//    @Override
//    public Map<String, Integer> getFacultyCourseCounts() {
//        return courseRepository.findAll().stream()
//                .collect(Collectors.groupingBy(
//                        c -> c.getFaculty().getName(),
//                        Collectors.summingInt(c -> 1)
//                ));
//    }
//
//    @Override
//    public Map<String, Integer> getDepartmentStudentCounts() {
//        return studentRepository.findAll().stream()
//                .collect(Collectors.groupingBy(
//                        s -> s.getDepartment().getName(),
//                        Collectors.summingInt(s -> 1)
//                ));
//    }
//
//    @Override
//    public Map<String, Double> getCourseProfessorRatios() {
//        return courseRepository.findAll().stream()
//                .collect(Collectors.toMap(
//                        Course::getTitle,
//                        c -> (double) c.getEnrolledStudents().size()
//                ));
//    }
//}