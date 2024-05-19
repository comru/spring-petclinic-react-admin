package io.amplicode.petclinic.dto.mapper;

import io.amplicode.petclinic.domain.PetType;
import io.amplicode.petclinic.dto.PetTypeDto;
import org.mapstruct.*;

@Mapper(config = MapStructConfig.class)
public interface PetTypeMapper {
    PetType toEntity(PetTypeDto petTypeDto);

    PetTypeDto toDto(PetType petType);

    PetType updateWithNull(PetTypeDto petTypeDto, @MappingTarget PetType petType);
}