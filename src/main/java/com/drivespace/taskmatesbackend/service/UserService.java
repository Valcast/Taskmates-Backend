package com.drivespace.taskmatesbackend.service;

import com.drivespace.taskmatesbackend.model.UserEntity;
import com.drivespace.taskmatesbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public UserEntity getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
