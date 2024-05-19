package io.amplicode.petclinic.domain;

import io.amplicode.petclinic.domain.base.NamedEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "specialties", indexes = {
        @Index(name = "idx_specialties_name", columnList = "name")
})
public class Specialty extends NamedEntity {

}