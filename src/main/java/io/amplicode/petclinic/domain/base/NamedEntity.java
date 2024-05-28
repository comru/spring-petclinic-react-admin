package io.amplicode.petclinic.domain.base;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class NamedEntity extends BaseEntity {

    @Column(name = "name", columnDefinition = "text")
    private String name;
}