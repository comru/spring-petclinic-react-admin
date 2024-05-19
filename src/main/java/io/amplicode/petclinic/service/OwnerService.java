package io.amplicode.petclinic.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.amplicode.petclinic.service.filter.OwnerFilter;
import io.amplicode.petclinic.domain.Owner;
import io.amplicode.petclinic.dto.OwnerDto;
import io.amplicode.petclinic.dto.mapper.OwnerMapper;
import io.amplicode.petclinic.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OwnerService {

    private final OwnerMapper ownerMapper;

    private final OwnerRepository ownerRepository;

    private final ObjectMapper objectMapper;

    public Page<OwnerDto> getList(OwnerFilter filter, Pageable pageable) {
        Specification<Owner> spec = filter.toSpecification();
        Page<Owner> owners = ownerRepository.findAll(spec, pageable);
        return owners.map(ownerMapper::toDto);
    }

    public OwnerDto getOne(Long id) {
        Optional<Owner> ownerOptional = ownerRepository.findById(id);
        return ownerMapper.toDto(ownerOptional.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id))));
    }

    public List<OwnerDto> getMany(List<Long> ids) {
        List<Owner> owners = ownerRepository.findAllById(ids);
        return owners.stream()
                .map(ownerMapper::toDto)
                .toList();
    }

    public OwnerDto create(OwnerDto dto) {
        Owner owner = ownerMapper.toEntity(dto);
        Owner resultOwner = ownerRepository.save(owner);
        return ownerMapper.toDto(resultOwner);
    }

    public OwnerDto patch(Long id, JsonNode patchNode) throws IOException {
        Owner owner = ownerRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id)));

        OwnerDto ownerDto = ownerMapper.toDto(owner);
        objectMapper.readerForUpdating(ownerDto).readValue(patchNode);
        ownerMapper.updateWithNull(ownerDto, owner);

        Owner resultOwner = ownerRepository.save(owner);
        return ownerMapper.toDto(resultOwner);
    }

    public List<Long> patchMany(List<Long> ids, JsonNode patchNode) throws IOException {
        Collection<Owner> owners = ownerRepository.findAllById(ids);

        for (Owner owner : owners) {
            OwnerDto ownerDto = ownerMapper.toDto(owner);
            objectMapper.readerForUpdating(ownerDto).readValue(patchNode);
            ownerMapper.updateWithNull(ownerDto, owner);
        }

        List<Owner> resultOwners = ownerRepository.saveAll(owners);
        return resultOwners.stream()
                .map(Owner::getId)
                .toList();
    }

    public OwnerDto delete(Long id) {
        Owner owner = ownerRepository.findById(id).orElse(null);
        if (owner != null) {
            ownerRepository.delete(owner);
        }
        return ownerMapper.toDto(owner);
    }

    public void deleteMany(List<Long> ids) {
        ownerRepository.deleteAllById(ids);
    }
}
