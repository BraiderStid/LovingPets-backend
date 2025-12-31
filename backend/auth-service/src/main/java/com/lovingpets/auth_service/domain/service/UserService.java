package com.lovingpets.auth_service.domain.service;

import com.lovingpets.auth_service.domain.dto.AdminCreateUserRequest;
import com.lovingpets.auth_service.domain.dto.CustomerRegisterRequest;
import com.lovingpets.auth_service.domain.dto.UpdateUserRequest;
import com.lovingpets.auth_service.domain.dto.UserResponse;
import com.lovingpets.auth_service.domain.exception.ConflictException;
import com.lovingpets.auth_service.domain.exception.NotFoundException;
import com.lovingpets.auth_service.domain.exception.UserNotFoundException;
import com.lovingpets.auth_service.domain.model.RoleName;
import com.lovingpets.auth_service.persistence.entity.RoleEntity;
import com.lovingpets.auth_service.persistence.entity.UserEntity;
import com.lovingpets.auth_service.persistence.entity.UserRoleEntity;
import com.lovingpets.auth_service.persistence.mapper.UserMapper;
import com.lovingpets.auth_service.persistence.repository.RoleRepository;
import com.lovingpets.auth_service.persistence.repository.UserRepository;
import com.lovingpets.auth_service.persistence.repository.UserRoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final UserRoleRepository userRoleRepository;

    public UserService(UserRepository userRepository,
                       UserMapper userMapper, UserRoleRepository userRoleRepository,
                       UserRoleService userRoleService,
                       RoleRepository roleRepository,
                       UserRoleRepository userRoleRepository1) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.roleRepository = roleRepository;
        this.userRoleRepository = userRoleRepository1;
    }

    public List<UserResponse> getAllUsers() {
        return userMapper.toResponseList(userRepository.findAll());
    }

    public UserResponse findById(Long id) {

        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        return userMapper.toResponse(userEntity);
    }

    public void disableUser(Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        if (!user.isEnabled()) {
            throw new ConflictException("User already disabled");
        }

        UserEntity updatedUser = userMapper.copyWithEnabled(user, false);
        userRepository.save(updatedUser);
    }

    public void enableUser(Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        if (user.isEnabled()) {
            throw new ConflictException("User already enabled");
        }

        UserEntity updatedUser = userMapper.copyWithEnabled(user, true);
        userRepository.save(updatedUser);
    }

    public UserResponse registerCustomer(CustomerRegisterRequest request) {

        if (userRepository.existsByEmail(request.email())) {
            throw new ConflictException("Email already registered");
        }
        // Crear usuario desde DTO
        UserEntity user = userMapper.fromCustomerRegisterRequest(request);

        // Guardar el usuario
        UserEntity savedUser = userRepository.save(user);

        // Asignar ROLE_CLIENTE automáticamente
        RoleEntity clientRole = roleRepository.findByName(RoleName.ROLE_CLIENT)
                .orElseThrow(() -> new RuntimeException("Role ROLE_CLIENT not found"));

        UserRoleEntity userRole = UserRoleEntity.builder()
                .user(savedUser)
                .role(clientRole)
                .build();

        userRoleRepository.save(userRole);

        // Devolver el UserResponse
        return userMapper.toResponse(savedUser);
    }

    public UserResponse createUserByAdmin(AdminCreateUserRequest request) {

        if (userRepository.existsByEmail(request.email())) {
            throw new ConflictException("Email already registered");
        }

        UserEntity user = userMapper.fromAdminCreateUser(request);
        UserEntity savedUser = userRepository.save(user);

        RoleEntity role = roleRepository.findByName(request.role())
                .orElseThrow(() -> new NotFoundException("Role not found"));

        UserRoleEntity userRole = UserRoleEntity.builder()
                .user(savedUser)
                .role(role)
                .build();

        userRoleRepository.save(userRole);

        return userMapper.toResponse(savedUser);
    }

    public UserResponse updateUser(Long userId, UpdateUserRequest request) {

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        if (request.email() != null &&
                userRepository.existsByEmailAndIdNot(request.email(), userId)) {
            throw new ConflictException("Email already registered");
        }

        UserEntity updatedUser = UserEntity.builder()
                .id(user.getId())
                .email(request.email() != null ? request.email() : user.getEmail())
                .password(request.password() != null ? request.password() : user.getPassword())
                .enabled(user.isEnabled())
                .createdAt(user.getCreatedAt())
                .roles(user.getRoles())
                .build();

        UserEntity savedUser = userRepository.save(updatedUser);

        return userMapper.toResponse(savedUser);
    }


}