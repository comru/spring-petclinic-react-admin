package io.amplicode.petclinic.api;

import com.fasterxml.jackson.databind.JsonNode;
import io.amplicode.petclinic.service.filter.OwnerFilter;
import io.amplicode.petclinic.dto.OwnerDto;
import io.amplicode.petclinic.service.OwnerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/owners")
@RequiredArgsConstructor
public class OwnerResource {

    private final OwnerService ownerService;

    @GetMapping
    public Page<OwnerDto> getList(@ParameterObject @ModelAttribute OwnerFilter filter, @ParameterObject Pageable pageable) {
        return ownerService.getList(filter, pageable);
    }

    @GetMapping("/{id}")
    public OwnerDto getOne(@PathVariable Long id) {
        return ownerService.getOne(id);
    }

    @GetMapping("/by-ids")
    public List<OwnerDto> getMany(@RequestParam List<Long> ids) {
        return ownerService.getMany(ids);
    }

    @PostMapping
    public OwnerDto create(@RequestBody @Valid OwnerDto dto) {
        return ownerService.create(dto);
    }

    @PatchMapping("/{id}")
    public OwnerDto patch(@PathVariable Long id, @RequestBody JsonNode patchNode) throws IOException {
        return ownerService.patch(id, patchNode);
    }

    @PatchMapping
    public List<Long> patchMany(@RequestParam List<Long> ids, @RequestBody JsonNode patchNode) throws IOException {
        return ownerService.patchMany(ids, patchNode);
    }

    @DeleteMapping("/{id}")
    public OwnerDto delete(@PathVariable Long id) {
        return ownerService.delete(id);
    }

    @DeleteMapping
    public void deleteMany(@RequestParam List<Long> ids) {
        ownerService.deleteMany(ids);
    }
}
