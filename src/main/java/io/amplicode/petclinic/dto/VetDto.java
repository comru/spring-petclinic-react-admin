package io.amplicode.petclinic.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;

/**
 * DTO for {@link io.amplicode.petclinic.domain.Vet}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record VetDto(Long id,
                     @NotBlank String firstName,
                     @NotBlank String lastName) {
}