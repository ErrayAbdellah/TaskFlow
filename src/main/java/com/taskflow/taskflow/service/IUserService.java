package com.taskflow.taskflow.service;

import com.taskflow.taskflow.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface IUserService {
    List<UserDTO> getAllUsers();

    UserDTO getUserById(UUID userId);

    UserDTO createUser(UserDTO userDTO);

    UserDTO updateUser(UUID userId, UserDTO updatedUser);

    void deleteUser(UUID userId);
}
