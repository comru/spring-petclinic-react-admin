package io.amplicode.petclinic.dto.mapper;

import io.amplicode.petclinic.domain.Visit;
import io.amplicode.petclinic.dto.VisitDto;
import org.mapstruct.*;

@Mapper(config = MapStructConfig.class)
public interface VisitMapper {
    Visit toEntity(VisitDto visitDto);

    VisitDto toDto(Visit visit);

    Visit updateWithNull(VisitDto visitDto, @MappingTarget Visit visit);
}