package com.College_directory.Springboot_first_app.service;

import com.College_directory.Springboot_first_app.dto.user.UserCreateDTO;
import com.College_directory.Springboot_first_app.dto.user.UserDTO;
import com.College_directory.Springboot_first_app.dto.user.UserUpdateDTO;
import com.College_directory.Springboot_first_app.model.User;

import java.util.List;

public interface UserServiceInterface {

      User createUser(UserCreateDTO userCreateDTO);

      User updateUser(Long id, UserUpdateDTO userUpdateDTO);

      User getUserById(Long id);

      User getUserByUsername(String username);

      List<User> getAllUsers();

      boolean changePassword(Long id, String oldPassword, String newPassword);

      boolean deleteUser(Long id);




//    User registerUser(UserDTO userDTO);
//
//    User getUserById(Long id);
////
//    User getUserByUsername(String username);
//
//    List<User> getAllUsers();
//
//    User updateUser(Long id, UserDTO userDTO);
//
//    boolean changePassword(Long id, String oldPassword, String newPassword);
//
//    boolean deleteUser(Long id);
//
////    // Uncomment these if implementing authentication and role management
////    /*
////    AuthToken authenticateUser(String username, String password);
////
////    Role getUserRole(Long id);
////    */
}
