package io.amplicode.petclinic.dto.mapper;

import io.amplicode.petclinic.domain.Pet;
import io.amplicode.petclinic.dto.PetDto;
import org.mapstruct.*;

@Mapper(config = MapStructConfig.class)
public interface PetMapper {
    @Mapping(source = "ownerId", target = "owner.id")
    @Mapping(source = "typeId", target = "type.id")
    Pet toEntity(PetDto petDto);

    @InheritInverseConfiguration(name = "toEntity")
    PetDto toDto(Pet pet);

    @Mapping(source = "ownerId", target = "owner")
    @Mapping(source = "typeId", target = "type")
    Pet updateWithNull(PetDto petDto, @MappingTarget Pet pet);
}