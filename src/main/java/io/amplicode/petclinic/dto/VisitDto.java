package io.amplicode.petclinic.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

/**
 * DTO for {@link io.amplicode.petclinic.domain.Visit}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record VisitDto(Long id,
                       LocalDate date,
                       @NotBlank String description) {
}