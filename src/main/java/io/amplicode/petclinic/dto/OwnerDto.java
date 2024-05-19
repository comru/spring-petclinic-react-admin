package io.amplicode.petclinic.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;

/**
 * DTO for {@link io.amplicode.petclinic.domain.Owner}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record OwnerDto(Long id,
                       @NotBlank String firstName,
                       @NotBlank String lastName,
                       @NotBlank String address,
                       @NotBlank String city,
                       @Digits(message = "Telephone must be a 10-digit number", integer = 10, fraction = 0) @NotBlank String telephone) {
}