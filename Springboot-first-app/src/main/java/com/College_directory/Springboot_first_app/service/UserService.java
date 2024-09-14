package com.College_directory.Springboot_first_app.service;

import com.College_directory.Springboot_first_app.model.User;
import com.College_directory.Springboot_first_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(UserDTO userDTO) {
        User user = convertToEntity(userDTO);
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(Long id, UserDTO userDTO) {
        User user = convertToEntity(userDTO);
        user.setId(id);
        return userRepository.save(user);
    }

    public boolean changePassword(Long id, String oldPassword, String newPassword) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null && user.getPassword().equals(oldPassword)) {
            user.setPassword(newPassword);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    /*
    public AuthToken authenticateUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return new AuthToken(generateToken(user)); // Token generation logic
        }
        return null;
    }
    */

    public Role getUserRole(Long id) {
        User user = userRepository.findById(id).orElse(null);
        return user != null ? user.getRole() : null;
    }

    private User convertToEntity(UserDTO userDTO) {
        // Conversion logic from UserDTO to User
    }

    private String generateToken(User user) {
        // Token generation logic
    }
}
