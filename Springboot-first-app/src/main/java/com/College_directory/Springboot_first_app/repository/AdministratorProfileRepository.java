package com.College_directory.Springboot_first_app.repository;



import com.College_directory.Springboot_first_app.model.AdministratorProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministratorProfileRepository extends JpaRepository<AdministratorProfile, Long> {
    AdministratorProfile findByUserId(Long Id);
}
