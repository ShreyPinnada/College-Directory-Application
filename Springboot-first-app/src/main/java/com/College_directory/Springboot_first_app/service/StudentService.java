package com.College_directory.Springboot_first_app.service;

import com.College_directory.Springboot_first_app.model.StudentProfile;
import com.College_directory.Springboot_first_app.repository.StudentProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentProfileRepository studentProfileRepository;

    public StudentProfile createStudentProfile(StudentProfile studentProfile) {
        return studentProfileRepository.save(studentProfile);
    }

}
