package io.amplicode.petclinic.domain.base;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Getter
@Setter
@MappedSuperclass
public abstract class Person extends BaseEntity {
    @Column(name = "first_name")
    @NotBlank
    @JdbcTypeCode(SqlTypes.LONG32VARCHAR)
    private String firstName;

    @Column(name = "last_name")
    @NotBlank
    @JdbcTypeCode(SqlTypes.LONG32VARCHAR)
    private String lastName;

}