package io.amplicode.petclinic.api;

import com.fasterxml.jackson.databind.JsonNode;
import io.amplicode.petclinic.dto.PetTypeDto;
import io.amplicode.petclinic.service.PetTypeService;
import io.amplicode.petclinic.service.filter.PetTypeFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/types")
@RequiredArgsConstructor
public class PetTypeResource {

    private final PetTypeService petTypeService;

    @GetMapping
    public Page<PetTypeDto> getList(@ModelAttribute PetTypeFilter filter, Pageable pageable) {
        return petTypeService.getList(filter, pageable);
    }

    @GetMapping("/{id}")
    public PetTypeDto getOne(@PathVariable Long id) {
        return petTypeService.getOne(id);
    }

    @GetMapping("/by-ids")
    public List<PetTypeDto> getMany(@RequestParam List<Long> ids) {
        return petTypeService.getMany(ids);
    }

    @PostMapping
    public PetTypeDto create(@RequestBody PetTypeDto dto) {
        return petTypeService.create(dto);
    }

    @PatchMapping("/{id}")
    public PetTypeDto patch(@PathVariable Long id, @RequestBody JsonNode patchNode) {
        return petTypeService.patch(id, patchNode);
    }

    @PatchMapping
    public List<Long> patchMany(@RequestParam List<Long> ids, @RequestBody JsonNode patchNode) {
        return petTypeService.patchMany(ids, patchNode);
    }

    @DeleteMapping("/{id}")
    public PetTypeDto delete(@PathVariable Long id) {
        return petTypeService.delete(id);
    }

    @DeleteMapping
    public void deleteMany(@RequestParam List<Long> ids) {
        petTypeService.deleteMany(ids);
    }
}
