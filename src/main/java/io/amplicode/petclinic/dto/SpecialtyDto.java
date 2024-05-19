package io.amplicode.petclinic.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * DTO for {@link io.amplicode.petclinic.domain.Specialty}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record SpecialtyDto(Long id, String name) {
}