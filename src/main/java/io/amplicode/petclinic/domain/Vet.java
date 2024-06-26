package io.amplicode.petclinic.domain;

import io.amplicode.petclinic.domain.base.Person;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "vets", indexes = {
        @Index(name = "idx_vets_last_name", columnList = "last_name")
})
public class Vet extends Person {

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "vet_specialties", joinColumns = @JoinColumn(name = "vet_id"),
            inverseJoinColumns = @JoinColumn(name = "specialty_id"))
    private Set<Specialty> specialties;

}