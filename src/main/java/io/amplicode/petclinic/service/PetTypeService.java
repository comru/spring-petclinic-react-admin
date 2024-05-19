package io.amplicode.petclinic.service;

import com.fasterxml.jackson.databind.JsonNode;
import io.amplicode.petclinic.domain.PetType;
import io.amplicode.petclinic.dto.PetTypeDto;
import io.amplicode.petclinic.dto.mapper.PetTypeMapper;
import io.amplicode.petclinic.repository.PetTypeRepository;
import io.amplicode.petclinic.service.filter.PetTypeFilter;
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
public class PetTypeService {

    private final PetTypeMapper petTypeMapper;

    private final PetTypeRepository petTypeRepository;

    private final ObjectPatcher objectPatcher;

    public Page<PetTypeDto> getList(PetTypeFilter filter, Pageable pageable) {
        Specification<PetType> spec = filter.toSpecification();
        Page<PetType> petTypes = petTypeRepository.findAll(spec, pageable);
        return petTypes.map(petTypeMapper::toDto);
    }

    public PetTypeDto getOne(Long id) {
        Optional<PetType> petTypeOptional = petTypeRepository.findById(id);
        return petTypeMapper.toDto(petTypeOptional.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id))));
    }

    public List<PetTypeDto> getMany(List<Long> ids) {
        List<PetType> petTypes = petTypeRepository.findAllById(ids);
        return petTypes.stream()
                .map(petTypeMapper::toDto)
                .toList();
    }

    public PetTypeDto create(PetTypeDto dto) {
        PetType petType = petTypeMapper.toEntity(dto);
        PetType resultPetType = petTypeRepository.save(petType);
        return petTypeMapper.toDto(resultPetType);
    }

    public PetTypeDto patch(Long id, JsonNode patchNode) {
        PetType petType = petTypeRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id)));

        PetTypeDto petTypeDto = petTypeMapper.toDto(petType);
        petTypeDto = objectPatcher.patchAndValidate(petTypeDto, patchNode);
        petTypeMapper.updateWithNull(petTypeDto, petType);

        PetType resultPetType = petTypeRepository.save(petType);
        return petTypeMapper.toDto(resultPetType);
    }

    public List<Long> patchMany(List<Long> ids, JsonNode patchNode) {
        Collection<PetType> petTypes = petTypeRepository.findAllById(ids);

        for (PetType petType : petTypes) {
            PetTypeDto petTypeDto = petTypeMapper.toDto(petType);
            petTypeDto = objectPatcher.patchAndValidate(petTypeDto, patchNode);
            petTypeMapper.updateWithNull(petTypeDto, petType);
        }

        List<PetType> resultPetTypes = petTypeRepository.saveAll(petTypes);
        return resultPetTypes.stream()
                .map(PetType::getId)
                .toList();
    }

    public PetTypeDto delete(Long id) {
        PetType petType = petTypeRepository.findById(id).orElse(null);
        if (petType != null) {
            petTypeRepository.delete(petType);
        }
        return petTypeMapper.toDto(petType);
    }

    public void deleteMany(List<Long> ids) {
        petTypeRepository.deleteAllById(ids);
    }
}
