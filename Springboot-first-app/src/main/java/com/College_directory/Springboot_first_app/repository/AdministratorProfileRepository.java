package com.College_directory.Springboot_first_app.repository;

import com.College_directory.Springboot_first_app.model.AdministratorProfile;
import com.College_directory.Springboot_first_app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministratorProfileRepository extends JpaRepository<AdministratorProfile, Long> {
    AdministratorProfile getByUser(User existingUser);

    AdministratorProfile getByUserId(Long userId);
}
