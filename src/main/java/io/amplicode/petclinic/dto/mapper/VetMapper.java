package io.amplicode.petclinic.dto.mapper;

import io.amplicode.petclinic.domain.Vet;
import io.amplicode.petclinic.dto.VetDto;
import org.mapstruct.*;

@Mapper(config = MapStructConfig.class)
public interface VetMapper {
    Vet toEntity(VetDto vetDto);

    VetDto toDto(Vet vet);

    Vet updateWithNull(VetDto vetDto, @MappingTarget Vet vet);
}