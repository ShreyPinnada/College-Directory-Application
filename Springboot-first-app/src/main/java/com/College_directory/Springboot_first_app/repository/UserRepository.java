package com.College_directory.Springboot_first_app.repository;

import com.College_directory.Springboot_first_app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}