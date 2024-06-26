package io.amplicode.petclinic.domain.base;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class Person extends BaseEntity {

    @Column(name = "first_name", columnDefinition = "text")
    @NotBlank
    private String firstName;

    @Column(name = "last_name", columnDefinition = "text")
    @NotBlank
    private String lastName;

}