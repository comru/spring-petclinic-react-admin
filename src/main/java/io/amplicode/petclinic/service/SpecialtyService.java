package io.amplicode.petclinic.service;

import com.fasterxml.jackson.databind.JsonNode;
import io.amplicode.petclinic.domain.Specialty;
import io.amplicode.petclinic.dto.SpecialtyDto;
import io.amplicode.petclinic.dto.mapper.SpecialtyMapper;
import io.amplicode.petclinic.repository.SpecialtyRepository;
import io.amplicode.petclinic.service.filter.SpecialtyFilter;
import io.amplicode.rautils.patch.ObjectPatcher;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SpecialtyService {

    private final SpecialtyMapper specialtyMapper;

    private final SpecialtyRepository specialtyRepository;

    private final ObjectPatcher objectPatcher;

    public Page<SpecialtyDto> getList(SpecialtyFilter filter, Pageable pageable) {
        Specification<Specialty> spec = filter.toSpecification();
        Page<Specialty> specialties = specialtyRepository.findAll(spec, pageable);
        return specialties.map(specialtyMapper::toDto);
    }

    public SpecialtyDto getOne(Long id) {
        Optional<Specialty> specialtyOptional = specialtyRepository.findById(id);
        return specialtyMapper.toDto(specialtyOptional.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id))));
    }

    public List<SpecialtyDto> getMany(List<Long> ids) {
        List<Specialty> specialties = specialtyRepository.findAllById(ids);
        return specialties.stream()
                .map(specialtyMapper::toDto)
                .toList();
    }

    public SpecialtyDto create(SpecialtyDto dto) {
        Specialty specialty = specialtyMapper.toEntity(dto);
        Specialty resultSpecialty = specialtyRepository.save(specialty);
        return specialtyMapper.toDto(resultSpecialty);
    }

    public SpecialtyDto patch(Long id, JsonNode patchNode) {
        Specialty specialty = specialtyRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id)));

        SpecialtyDto specialtyDto = specialtyMapper.toDto(specialty);
        specialtyDto = objectPatcher.patchAndValidate(specialtyDto, patchNode);
        specialtyMapper.updateWithNull(specialtyDto, specialty);

        Specialty resultSpecialty = specialtyRepository.save(specialty);
        return specialtyMapper.toDto(resultSpecialty);
    }

    public List<Long> patchMany(List<Long> ids, JsonNode patchNode) {
        Collection<Specialty> specialties = specialtyRepository.findAllById(ids);

        for (Specialty specialty : specialties) {
            SpecialtyDto specialtyDto = specialtyMapper.toDto(specialty);
            specialtyDto = objectPatcher.patchAndValidate(specialtyDto, patchNode);
            specialtyMapper.updateWithNull(specialtyDto, specialty);
        }

        List<Specialty> resultSpecialties = specialtyRepository.saveAll(specialties);
        return resultSpecialties.stream()
                .map(Specialty::getId)
                .toList();
    }

    public SpecialtyDto delete(Long id) {
        Specialty specialty = specialtyRepository.findById(id).orElse(null);
        if (specialty != null) {
            specialtyRepository.delete(specialty);
        }
        return specialtyMapper.toDto(specialty);
    }

    public void deleteMany(List<Long> ids) {
        specialtyRepository.deleteAllById(ids);
    }
}
