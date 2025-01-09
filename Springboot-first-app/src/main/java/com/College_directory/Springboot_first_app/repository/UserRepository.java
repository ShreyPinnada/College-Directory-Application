package com.College_directory.Springboot_first_app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.College_directory.Springboot_first_app.model.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    User findByEmail(@Email @Size(max = 100) String email);
}