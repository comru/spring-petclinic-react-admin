package io.amplicode.petclinic.api;

import com.fasterxml.jackson.databind.JsonNode;
import io.amplicode.petclinic.dto.VisitDto;
import io.amplicode.petclinic.service.VisitService;
import io.amplicode.petclinic.service.filter.VisitFilter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/visits")
@RequiredArgsConstructor
public class VisitResource {

    private final VisitService visitService;

    @GetMapping
    public Page<VisitDto> getList(@ModelAttribute VisitFilter filter, Pageable pageable) {
        return visitService.getList(filter, pageable);
    }

    @GetMapping("/{id}")
    public VisitDto getOne(@PathVariable Long id) {
        return visitService.getOne(id);
    }

    @GetMapping("/by-ids")
    public List<VisitDto> getMany(@RequestParam List<Long> ids) {
        return visitService.getMany(ids);
    }

    @PostMapping
    public VisitDto create(@RequestBody @Valid VisitDto dto) {
        return visitService.create(dto);
    }

    @PatchMapping("/{id}")
    public VisitDto patch(@PathVariable Long id, @RequestBody JsonNode patchNode) {
        return visitService.patch(id, patchNode);
    }

    @PatchMapping
    public List<Long> patchMany(@RequestParam List<Long> ids, @RequestBody JsonNode patchNode) {
        return visitService.patchMany(ids, patchNode);
    }

    @DeleteMapping("/{id}")
    public VisitDto delete(@PathVariable Long id) {
        return visitService.delete(id);
    }

    @DeleteMapping
    public void deleteMany(@RequestParam List<Long> ids) {
        visitService.deleteMany(ids);
    }
}
