package com.College_directory.Springboot_first_app.service.implement;

import com.College_directory.Springboot_first_app.dto.UserDTO;
import com.College_directory.Springboot_first_app.model.User;
import com.College_directory.Springboot_first_app.repository.UserRepository;
import com.College_directory.Springboot_first_app.service.UserServiceInterface;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImplement implements UserServiceInterface {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public User registerUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);  // Map UserDTO to User
        //user.setPassword(passwordEncoder.encode(userDTO.getPassword()));  // Encode password
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(Long id, UserDTO userDTO) {
        User user = getUserById(id);
        if (user != null) {
            modelMapper.map(userDTO, user);  // Update user fields with UserDTO values
            return userRepository.save(user);
        }
        return null;
    }

    @Override
    public boolean changePassword(Long id, String oldPassword, String newPassword) {
        User user = getUserById(id);
        if (user != null && passwordEncoder.matches(oldPassword, user.getPassword())) {
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
