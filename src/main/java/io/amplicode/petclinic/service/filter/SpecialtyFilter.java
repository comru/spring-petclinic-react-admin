package io.amplicode.petclinic.service.filter;

import io.amplicode.petclinic.domain.Specialty;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public record SpecialtyFilter(String nameContains) {
    public Specification<Specialty> toSpecification() {
        return Specification.where(nameContainsSpec());
    }

    private Specification<Specialty> nameContainsSpec() {
        return ((root, query, cb) -> StringUtils.hasText(nameContains)
                ? cb.like(cb.lower(root.get("name")), "%" + nameContains.toLowerCase() + "%")
                : null);
    }
}