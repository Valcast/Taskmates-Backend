package com.taskmates.backend.service;

import com.taskmates.backend.model.dto.UserDTO;
import com.taskmates.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO createUser(UserDTO user) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public UserDTO getUserById(UUID id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public UserDTO updateUser(UUID id, UserDTO user) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public void deleteUser(UUID id) {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
