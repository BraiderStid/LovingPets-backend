package com.lovingpets.pet_service.persistence.mapper;

import com.lovingpets.pet_service.domain.dto.PetDto;
import com.lovingpets.pet_service.domain.dto.PetUpdateDto;
import com.lovingpets.pet_service.persistence.entity.PetEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PetMapper {

    PetDto toDto(PetEntity entity);
    List<PetDto> toDto(Iterable<PetEntity> entities);

    PetEntity toEntity(PetDto petDto);

    void updateEntityFromDto(PetUpdateDto petUpdateDto,@MappingTarget PetEntity petEntity);

}
