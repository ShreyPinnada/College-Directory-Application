package com.College_directory.Springboot_first_app.repository;


import com.College_directory.Springboot_first_app.model.FacultyProfile;
import com.College_directory.Springboot_first_app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyProfileRepository extends JpaRepository<FacultyProfile, Long> {
    FacultyProfile getByUser(User existingUser);

    FacultyProfile getByUserId(Long userId);
}
