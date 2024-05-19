package io.amplicode.petclinic.api;

import com.fasterxml.jackson.databind.JsonNode;
import io.amplicode.petclinic.dto.VetDto;
import io.amplicode.petclinic.service.VetService;
import io.amplicode.petclinic.service.filter.VetFilter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vets")
@RequiredArgsConstructor
public class VetResource {

    private final VetService vetService;

    @GetMapping
    public Page<VetDto> getList(@ModelAttribute VetFilter filter, Pageable pageable) {
        return vetService.getList(filter, pageable);
    }

    @GetMapping("/{id}")
    public VetDto getOne(@PathVariable Long id) {
        return vetService.getOne(id);
    }

    @GetMapping("/by-ids")
    public List<VetDto> getMany(@RequestParam List<Long> ids) {
        return vetService.getMany(ids);
    }

    @PostMapping
    public VetDto create(@RequestBody @Valid VetDto dto) {
        return vetService.create(dto);
    }

    @PatchMapping("/{id}")
    public VetDto patch(@PathVariable Long id, @RequestBody JsonNode patchNode) {
        return vetService.patch(id, patchNode);
    }

    @PatchMapping
    public List<Long> patchMany(@RequestParam List<Long> ids, @RequestBody JsonNode patchNode) {
        return vetService.patchMany(ids, patchNode);
    }

    @DeleteMapping("/{id}")
    public VetDto delete(@PathVariable Long id) {
        return vetService.delete(id);
    }

    @DeleteMapping
    public void deleteMany(@RequestParam List<Long> ids) {
        vetService.deleteMany(ids);
    }
}
