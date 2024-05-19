package io.amplicode.petclinic.service;

import com.fasterxml.jackson.databind.JsonNode;
import io.amplicode.petclinic.domain.Visit;
import io.amplicode.petclinic.dto.VisitDto;
import io.amplicode.petclinic.dto.mapper.VisitMapper;
import io.amplicode.petclinic.repository.VisitRepository;
import io.amplicode.petclinic.service.filter.VisitFilter;
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
public class VisitService {

    private final VisitMapper visitMapper;

    private final VisitRepository visitRepository;

    private final ObjectPatcher objectPatcher;

    public Page<VisitDto> getList(VisitFilter filter, Pageable pageable) {
        Specification<Visit> spec = filter.toSpecification();
        Page<Visit> visits = visitRepository.findAll(spec, pageable);
        return visits.map(visitMapper::toDto);
    }

    public VisitDto getOne(Long id) {
        Optional<Visit> visitOptional = visitRepository.findById(id);
        return visitMapper.toDto(visitOptional.orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id))));
    }

    public List<VisitDto> getMany(List<Long> ids) {
        List<Visit> visits = visitRepository.findAllById(ids);
        return visits.stream()
                .map(visitMapper::toDto)
                .toList();
    }

    public VisitDto create(VisitDto dto) {
        Visit visit = visitMapper.toEntity(dto);
        Visit resultVisit = visitRepository.save(visit);
        return visitMapper.toDto(resultVisit);
    }

    public VisitDto patch(Long id, JsonNode patchNode) {
        Visit visit = visitRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Entity with id `%s` not found".formatted(id)));

        VisitDto visitDto = visitMapper.toDto(visit);
        visitDto = objectPatcher.patchAndValidate(visitDto, patchNode);
        visitMapper.updateWithNull(visitDto, visit);

        Visit resultVisit = visitRepository.save(visit);
        return visitMapper.toDto(resultVisit);
    }

    public List<Long> patchMany(List<Long> ids, JsonNode patchNode) {
        Collection<Visit> visits = visitRepository.findAllById(ids);

        for (Visit visit : visits) {
            VisitDto visitDto = visitMapper.toDto(visit);
            visitDto = objectPatcher.patchAndValidate(visitDto, patchNode);
            visitMapper.updateWithNull(visitDto, visit);
        }

        List<Visit> resultVisits = visitRepository.saveAll(visits);
        return resultVisits.stream()
                .map(Visit::getId)
                .toList();
    }

    public VisitDto delete(Long id) {
        Visit visit = visitRepository.findById(id).orElse(null);
        if (visit != null) {
            visitRepository.delete(visit);
        }
        return visitMapper.toDto(visit);
    }

    public void deleteMany(List<Long> ids) {
        visitRepository.deleteAllById(ids);
    }
}
