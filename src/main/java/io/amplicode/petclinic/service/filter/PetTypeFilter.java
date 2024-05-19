package io.amplicode.petclinic.service.filter;

import io.amplicode.petclinic.domain.PetType;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public record PetTypeFilter(String nameContains) {
    public Specification<PetType> toSpecification() {
        return Specification.where(nameContainsSpec());
    }

    private Specification<PetType> nameContainsSpec() {
        return ((root, query, cb) -> StringUtils.hasText(nameContains)
                ? cb.like(cb.lower(root.get("name")), "%" + nameContains.toLowerCase() + "%")
                : null);
    }
}