package io.amplicode.petclinic.domain.base;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Getter
@Setter
@MappedSuperclass
public abstract class NamedEntity extends BaseEntity {

    @Column(name = "name")
    @JdbcTypeCode(SqlTypes.LONG32VARCHAR)
    private String name;
}