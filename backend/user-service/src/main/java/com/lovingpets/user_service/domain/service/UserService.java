package com.lovingpets.user_service.domain.service;

import com.lovingpets.user_service.config.JwtAuthenticationToken;
import com.lovingpets.user_service.domain.dto.CreateUserProfileRequest;
import com.lovingpets.user_service.domain.dto.UpdateUserRequest;
import com.lovingpets.user_service.domain.dto.UserDto;
import com.lovingpets.user_service.domain.exception.PhoneNumberAlreadyExistsException;
import com.lovingpets.user_service.domain.exception.UserNotFoundException;
import com.lovingpets.user_service.domain.exception.UserProfileAlreadyExistsException;
import com.lovingpets.user_service.persistence.entity.UserEntity;
import com.lovingpets.user_service.persistence.mapper.UserMapper;
import com.lovingpets.user_service.persistence.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository,
                       UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserDto> getAll() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .map(userMapper::toDto)
                .toList();
    }

    public UserDto getById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public UserDto getMyProfile() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        JwtAuthenticationToken jwtAuth =
                (JwtAuthenticationToken) authentication;

        Long userId = (Long) jwtAuth.getPrincipal();

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        return userMapper.toDto(user);
    }

    @Transactional
    public UserDto update(Long id, UpdateUserRequest request) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        if (request.firstName() != null || request.lastName() != null) {
            user.updateName(request.firstName(), request.lastName());
        }

        if (request.phoneNumber() != null) {

            boolean phoneInUse = userRepository.existsByPhoneNumber(request.phoneNumber());

            if (phoneInUse && !request.phoneNumber().equals(user.getPhoneNumber())) {
                throw new PhoneNumberAlreadyExistsException(request.phoneNumber());
            }

            user.updatePhoneNumber(request.phoneNumber());
        }

        if (request.dateOfBirth() != null) {
            user.updateDateOfBirth(request.dateOfBirth());
        }

        return userMapper.toDto(user);
    }

    @Transactional
    public UserDto updateSelf(Long userId, UpdateUserRequest request) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        if (request.firstName() != null || request.lastName() != null) {
            user.updateName(request.firstName(), request.lastName());
        }

        if (request.phoneNumber() != null) {
            boolean phoneInUse = userRepository.existsByPhoneNumber(request.phoneNumber());
            if (phoneInUse && !request.phoneNumber().equals(user.getPhoneNumber())) {
                throw new PhoneNumberAlreadyExistsException(request.phoneNumber());
            }
            user.updatePhoneNumber(request.phoneNumber());
        }

        if (request.dateOfBirth() != null) {
            user.updateDateOfBirth(request.dateOfBirth());
        }

        return userMapper.toDto(user);
    }

    @Transactional
    public UserDto createProfile(CreateUserProfileRequest request) {

        if (userRepository.existsById(request.id())) {
            throw new UserProfileAlreadyExistsException(request.id());
        }

        if (request.phoneNumber() != null &&
                userRepository.existsByPhoneNumber(request.phoneNumber())) {
            throw new PhoneNumberAlreadyExistsException(request.phoneNumber());
        }

        UserEntity user = userMapper.toEntity(request);
        UserEntity savedUser = userRepository.save(user);

        return userMapper.toDto(savedUser);
    }
}
