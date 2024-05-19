package io.amplicode.petclinic.service;

import com.fasterxml.jackson.databind.JsonNode;
import io.amplicode.petclinic.domain.Pet;
import io.amplicode.petclinic.dto.PetDto;
import io.amplicode.petclinic.dto.mapper.PetMapper;
import io.amplicode.petclinic.repository.PetRepository;
import io.amplicode.petclinic.service.filter.PetFilter;
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
public class PetService {

    private final PetMapper petMapper;

    private final PetRepository petRepository;

    private final ObjectPatcher objectPatcher;

    public Page<PetDto> getList(PetFilter filter, Pageable pageable) {
        Specification<Pet> spec = filter.toSpecification();
        Page<Pet> pets = petRepository.findAll(spec, pageable);
        return pets.map(petMapper::toDto);
    }

    public PetDto getOne(Long id) {
        Optional<Pet> petOptional = petRepository.findById(id);
        return petMapper.toDto(petOptional.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id))));
    }

    public List<PetDto> getMany(List<Long> ids) {
        List<Pet> pets = petRepository.findAllById(ids);
        return pets.stream()
                .map(petMapper::toDto)
                .toList();
    }

    public PetDto create(PetDto dto) {
        Pet pet = petMapper.toEntity(dto);
        Pet resultPet = petRepository.save(pet);
        return petMapper.toDto(resultPet);
    }

    public PetDto patch(Long id, JsonNode patchNode) {
        Pet pet = petRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id)));

        PetDto petDto = petMapper.toDto(pet);
        petDto = objectPatcher.patchAndValidate(petDto, patchNode);
        petMapper.updateWithNull(petDto, pet);

        Pet resultPet = petRepository.save(pet);
        return petMapper.toDto(resultPet);
    }

    public List<Long> patchMany(List<Long> ids, JsonNode patchNode) {
        Collection<Pet> pets = petRepository.findAllById(ids);

        for (Pet pet : pets) {
            PetDto petDto = petMapper.toDto(pet);
            petDto = objectPatcher.patchAndValidate(petDto, patchNode);
            petMapper.updateWithNull(petDto, pet);
        }

        List<Pet> resultPets = petRepository.saveAll(pets);
        return resultPets.stream()
                .map(Pet::getId)
                .toList();
    }

    public PetDto delete(Long id) {
        Pet pet = petRepository.findById(id).orElse(null);
        if (pet != null) {
            petRepository.delete(pet);
        }
        return petMapper.toDto(pet);
    }

    public void deleteMany(List<Long> ids) {
        petRepository.deleteAllById(ids);
    }
}
