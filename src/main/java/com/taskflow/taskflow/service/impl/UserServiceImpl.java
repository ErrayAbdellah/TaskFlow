package com.taskflow.taskflow.service.impl;

import com.taskflow.taskflow.dto.UserDTO;

import com.taskflow.taskflow.entity.User;
import com.taskflow.taskflow.repository.IUserRepo;
import com.taskflow.taskflow.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Component
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final IUserRepo userRepo;
    private final ModelMapper modelMapper;
    @Override
    public List<UserDTO> getAllUsers() {
        return userRepo.findAll()
                .stream()
                .map(user -> modelMapper.map(user,UserDTO.class))
                .toList();
    }

    @Override
    public UserDTO getUserById(UUID userId) {
        Optional<User> user = userRepo.findById(userId);
        UserDTO userDTO = modelMapper.map(user.get(),UserDTO.class);
        return userDTO ;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO,User.class);
        userRepo.save(user);
        return null;
    }

    @Override
    public UserDTO updateUser(UUID userId, UserDTO updatedUser) {
        updatedUser.setId(userId);
//        return userRepo.save(updatedUser);
    return null;
    }

    @Override
    public void deleteUser(UUID userId) {
        userRepo.deleteById(userId);
    }
}
