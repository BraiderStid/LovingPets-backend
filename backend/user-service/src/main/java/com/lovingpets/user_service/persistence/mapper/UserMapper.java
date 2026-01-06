package com.lovingpets.user_service.persistence.mapper;

import com.lovingpets.user_service.domain.dto.CreateUserProfileRequest;
import com.lovingpets.user_service.domain.dto.UserDto;
import com.lovingpets.user_service.persistence.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toDto(UserEntity entity);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    UserEntity toEntity(CreateUserProfileRequest request);

}
