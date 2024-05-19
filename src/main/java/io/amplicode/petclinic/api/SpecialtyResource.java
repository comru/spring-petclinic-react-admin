package io.amplicode.petclinic.api;

import com.fasterxml.jackson.databind.JsonNode;
import io.amplicode.petclinic.dto.SpecialtyDto;
import io.amplicode.petclinic.service.SpecialtyService;
import io.amplicode.petclinic.service.filter.SpecialtyFilter;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/specialties")
@RequiredArgsConstructor
public class SpecialtyResource {

    private final SpecialtyService specialty;

    @GetMapping
    public Page<SpecialtyDto> getList(@ParameterObject @ModelAttribute SpecialtyFilter filter, @ParameterObject Pageable pageable) {
        return specialty.getList(filter, pageable);
    }

    @GetMapping("/{id}")
    public SpecialtyDto getOne(@PathVariable Long id) {
        return specialty.getOne(id);
    }

    @GetMapping("/by-ids")
    public List<SpecialtyDto> getMany(@RequestParam List<Long> ids) {
        return specialty.getMany(ids);
    }

    @PostMapping
    public SpecialtyDto create(@RequestBody SpecialtyDto dto) {
        return specialty.create(dto);
    }

    @PatchMapping("/{id}")
    public SpecialtyDto patch(@PathVariable Long id, @RequestBody JsonNode patchNode) {
        return specialty.patch(id, patchNode);
    }

    @PatchMapping
    public List<Long> patchMany(@RequestParam List<Long> ids, @RequestBody JsonNode patchNode) {
        return specialty.patchMany(ids, patchNode);
    }

    @DeleteMapping("/{id}")
    public SpecialtyDto delete(@PathVariable Long id) {
        return specialty.delete(id);
    }

    @DeleteMapping
    public void deleteMany(@RequestParam List<Long> ids) {
        specialty.deleteMany(ids);
    }
}
