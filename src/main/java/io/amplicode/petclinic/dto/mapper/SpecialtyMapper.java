package io.amplicode.petclinic.dto.mapper;

import io.amplicode.petclinic.domain.Specialty;
import io.amplicode.petclinic.dto.SpecialtyDto;
import org.mapstruct.*;

@Mapper(config = MapStructConfig.class)
public interface SpecialtyMapper {
    Specialty toEntity(SpecialtyDto specialtyDto);

    SpecialtyDto toDto(Specialty specialty);

    Specialty updateWithNull(SpecialtyDto specialtyDto, @MappingTarget Specialty specialty);
}