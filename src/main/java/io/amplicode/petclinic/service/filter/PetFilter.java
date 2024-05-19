package io.amplicode.petclinic.service.filter;

import io.amplicode.petclinic.domain.Pet;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public record PetFilter(String nameContains) {
    public Specification<Pet> toSpecification() {
        return Specification.where(nameContainsSpec());
    }

    private Specification<Pet> nameContainsSpec() {
        return ((root, query, cb) -> StringUtils.hasText(nameContains)
                ? cb.like(cb.lower(root.get("name")), "%" + nameContains.toLowerCase() + "%")
                : null);
    }
}