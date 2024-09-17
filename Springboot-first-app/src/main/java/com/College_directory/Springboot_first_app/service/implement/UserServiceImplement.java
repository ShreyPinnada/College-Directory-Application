package com.College_directory.Springboot_first_app.service.implement;


import com.College_directory.Springboot_first_app.dto.user.UserCreateDTO;
import com.College_directory.Springboot_first_app.dto.user.UserUpdateDTO;
import com.College_directory.Springboot_first_app.model.User;
import com.College_directory.Springboot_first_app.repository.UserRepository;
import com.College_directory.Springboot_first_app.service.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplement implements UserServiceInterface {

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Override
    public User createUser(UserCreateDTO userCreateDTO) {
        Optional<User> existingUser = userRepository.findByUsername(userCreateDTO.getUsername());
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("User with username " + userCreateDTO.getUsername() + " already exists");
        }
        User newUser = new User();
        newUser.setUsername(userCreateDTO.getUsername());
        newUser.setPassword(userCreateDTO.getPassword());
        newUser.setName(userCreateDTO.getName());
        newUser.setEmail(userCreateDTO.getEmail());
        newUser.setPhone(userCreateDTO.getPhone());
        newUser.setRole(userCreateDTO.getRole());
        return userRepository.save(newUser);
    }

    @Override
    public User updateUser(Long id, UserUpdateDTO userUpdateDTO) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userUpdateDTO.getId()));

        // Check if the new email already belongs to another user
        User userWithSameEmail = userRepository.findByEmail(userUpdateDTO.getEmail());
        if (userWithSameEmail != null && !userWithSameEmail.getId().equals(existingUser.getId())) {
            throw new IllegalArgumentException("Email " + userUpdateDTO.getEmail() + " is already in use");
        }

        if (userUpdateDTO.getEmail() != null) {
            existingUser.setEmail(userUpdateDTO.getEmail());
        }
        if (userUpdateDTO.getName() != null) {
            existingUser.setName(userUpdateDTO.getName());
        }
        if (userUpdateDTO.getPhone() != null) {
            existingUser.setPhone(userUpdateDTO.getPhone());
        }
        if (userUpdateDTO.getPassword() != null) {
            existingUser.setPassword(userUpdateDTO.getPassword()); // Ensure password is encoded
        }
        return userRepository.save(existingUser);
    }

    @Override
    public User getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
        return user;
    }

    @Override
    public User getUserByUsername(String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        return optionalUser.orElseThrow(() ->
                new IllegalArgumentException("User with username " + username + " does not exist"));
    }


    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public boolean changePassword(Long id, String oldPassword, String newPassword) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User with id " + id + " not found"));
        if (!user.getPassword().equals(oldPassword)) {
            // If the old password doesn't match, return false or throw an exception
            throw new IllegalArgumentException("Old password is incorrect");
        }
//      user.setPassword(passwordEncoder.encode(newPassword));
        user.setPassword(newPassword);
        userRepository.save(user);
        return true;
    }

    @Override
    public boolean deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User with id "+ id + " not found"));
        userRepository.delete(user);
        return true;
    }
}
