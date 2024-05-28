package io.amplicode.petclinic.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * DTO for {@link io.amplicode.petclinic.domain.Pet}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record PetMinimalDto(Long id, String name) {
}