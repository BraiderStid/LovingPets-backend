package com.lovingpets.auth_service.domain.service;

import com.lovingpets.auth_service.domain.dto.UserResponse;
import com.lovingpets.auth_service.persistence.mapper.UserMapper;
import com.lovingpets.auth_service.persistence.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository,
                       UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserResponse> getAllUsers() {
        return userMapper.toResponseList(userRepository.findAll());
    }
}