package io.amplicode.petclinic.dto.mapper;

import io.amplicode.petclinic.domain.Owner;
import io.amplicode.petclinic.dto.OwnerDto;
import org.mapstruct.*;

@Mapper(config = MapStructConfig.class)
public interface OwnerMapper {
    Owner toEntity(OwnerDto ownerDto);

    OwnerDto toDto(Owner owner);

    Owner updateWithNull(OwnerDto ownerDto, @MappingTarget Owner owner);
}