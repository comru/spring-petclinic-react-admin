package io.amplicode.petclinic.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;

/**
 * DTO for {@link io.amplicode.petclinic.domain.Pet}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record PetDto(Long id,
                     String name,
                     LocalDate birthDate,
                     Long typeId,
                     Long ownerId) {
}