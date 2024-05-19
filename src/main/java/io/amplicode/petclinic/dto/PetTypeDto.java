package io.amplicode.petclinic.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * DTO for {@link io.amplicode.petclinic.domain.PetType}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record PetTypeDto(Long id, String name) {
}