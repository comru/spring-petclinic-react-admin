package io.amplicode.petclinic.api;

import com.fasterxml.jackson.databind.JsonNode;
import io.amplicode.petclinic.dto.PetDto;
import io.amplicode.petclinic.service.PetService;
import io.amplicode.petclinic.service.filter.PetFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pets")
@RequiredArgsConstructor
public class PetResource {

    private final PetService petService;

    @GetMapping
    public Page<PetDto> getList(@ModelAttribute PetFilter filter, Pageable pageable) {
        return petService.getList(filter, pageable);
    }

    @GetMapping("/{id}")
    public PetDto getOne(@PathVariable Long id) {
        return petService.getOne(id);
    }

    @GetMapping("/by-ids")
    public List<PetDto> getMany(@RequestParam List<Long> ids) {
        return petService.getMany(ids);
    }

    @PostMapping
    public PetDto create(@RequestBody PetDto dto) {
        return petService.create(dto);
    }

    @PatchMapping("/{id}")
    public PetDto patch(@PathVariable Long id, @RequestBody JsonNode patchNode) {
        return petService.patch(id, patchNode);
    }

    @PatchMapping
    public List<Long> patchMany(@RequestParam List<Long> ids, @RequestBody JsonNode patchNode) {
        return petService.patchMany(ids, patchNode);
    }

    @DeleteMapping("/{id}")
    public PetDto delete(@PathVariable Long id) {
        return petService.delete(id);
    }

    @DeleteMapping
    public void deleteMany(@RequestParam List<Long> ids) {
        petService.deleteMany(ids);
    }
}
