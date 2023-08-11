package com.project.AdminPanel.service;

import com.project.AdminPanel.entity.Role;
import com.project.AdminPanel.entity.User;
import com.project.AdminPanel.model.UserDTO;
import com.project.AdminPanel.repository.RoleRepository;
import com.project.AdminPanel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void saveUser(UserDTO userDto) {
        Role role = roleRepository.findByName(userDto.getRole());

        if (role == null) {
            role = roleRepository.save(new Role(userDto.getRole()));
        }

        User user = new User(userDto.getName(), userDto.getEmail(), passwordEncoder.encode(userDto.getPassword()),
                Arrays.asList(role));
        userRepository.save(user);
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}