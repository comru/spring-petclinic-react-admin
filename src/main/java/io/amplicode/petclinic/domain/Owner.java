package io.amplicode.petclinic.domain;

import io.amplicode.petclinic.domain.base.Person;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "owners", indexes = {
        @Index(name = "idx_owners_last_name", columnList = "last_name")
})
public class Owner extends Person {

    @Column(name = "address", columnDefinition = "text")
    @NotBlank
    private String address;

    @Column(name = "city", columnDefinition = "text")
    @NotBlank
    private String city;

    @Column(name = "telephone", length = 10)
    @NotBlank
    @Digits(fraction = 0, integer = 10, message = "Telephone must be a 10-digit number")
    private String telephone;

    @OneToMany(mappedBy = "owner", orphanRemoval = true)
    @BatchSize(size = 10)
    private Set<Pet> pets = new LinkedHashSet<>();

}