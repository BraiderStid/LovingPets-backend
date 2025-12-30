package com.lovingpets.auth_service.persistence.mapper;

import com.lovingpets.auth_service.domain.dto.UserResponse;
import com.lovingpets.auth_service.persistence.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "roles", expression = "java(mapRoles(user))")
    UserResponse toResponse(UserEntity user);

    List<UserResponse> toResponseList(Iterable<UserEntity> users);

    default List<String> mapRoles(UserEntity user) {
        if (user.getRoles() == null) return List.of();

        return user.getRoles().stream()
                .map(role -> role.getRole().getName().name())
                .collect(Collectors.toList());
    }
}