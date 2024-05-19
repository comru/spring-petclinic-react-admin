package io.amplicode.petclinic.service;

import com.fasterxml.jackson.databind.JsonNode;
import io.amplicode.petclinic.domain.Vet;
import io.amplicode.petclinic.dto.VetDto;
import io.amplicode.petclinic.dto.mapper.VetMapper;
import io.amplicode.petclinic.repository.VetRepository;
import io.amplicode.petclinic.service.filter.VetFilter;
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
public class VetService {

    private final VetMapper vetMapper;

    private final VetRepository vetRepository;

    private final ObjectPatcher objectPatcher;

    public Page<VetDto> getList(VetFilter filter, Pageable pageable) {
        Specification<Vet> spec = filter.toSpecification();
        Page<Vet> vets = vetRepository.findAll(spec, pageable);
        return vets.map(vetMapper::toDto);
    }

    public VetDto getOne(Long id) {
        Optional<Vet> vetOptional = vetRepository.findById(id);
        return vetMapper.toDto(vetOptional.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id))));
    }

    public List<VetDto> getMany(List<Long> ids) {
        List<Vet> vets = vetRepository.findAllById(ids);
        return vets.stream()
                .map(vetMapper::toDto)
                .toList();
    }

    public VetDto create(VetDto dto) {
        Vet vet = vetMapper.toEntity(dto);
        Vet resultVet = vetRepository.save(vet);
        return vetMapper.toDto(resultVet);
    }

    public VetDto patch(Long id, JsonNode patchNode) {
        Vet vet = vetRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id)));

        VetDto vetDto = vetMapper.toDto(vet);
        vetDto = objectPatcher.patchAndValidate(vetDto, patchNode);
        vetMapper.updateWithNull(vetDto, vet);

        Vet resultVet = vetRepository.save(vet);
        return vetMapper.toDto(resultVet);
    }

    public List<Long> patchMany(List<Long> ids, JsonNode patchNode) {
        Collection<Vet> vets = vetRepository.findAllById(ids);

        for (Vet vet : vets) {
            VetDto vetDto = vetMapper.toDto(vet);
            vetDto = objectPatcher.patchAndValidate(vetDto, patchNode);
            vetMapper.updateWithNull(vetDto, vet);
        }

        List<Vet> resultVets = vetRepository.saveAll(vets);
        return resultVets.stream()
                .map(Vet::getId)
                .toList();
    }

    public VetDto delete(Long id) {
        Vet vet = vetRepository.findById(id).orElse(null);
        if (vet != null) {
            vetRepository.delete(vet);
        }
        return vetMapper.toDto(vet);
    }

    public void deleteMany(List<Long> ids) {
        vetRepository.deleteAllById(ids);
    }
}
