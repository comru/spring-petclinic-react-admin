package io.amplicode.petclinic.service.filter;

import io.amplicode.petclinic.domain.Vet;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public record VetFilter(String firstNameContains, String lastNameContains) {
    public Specification<Vet> toSpecification() {
        return Specification.where(firstNameContainsSpec())
                .or(lastNameContainsSpec());
    }

    private Specification<Vet> firstNameContainsSpec() {
        return ((root, query, cb) -> StringUtils.hasText(firstNameContains)
                ? cb.like(cb.lower(root.get("firstName")), "%" + firstNameContains.toLowerCase() + "%")
                : null);
    }

    private Specification<Vet> lastNameContainsSpec() {
        return ((root, query, cb) -> StringUtils.hasText(lastNameContains)
                ? cb.like(cb.lower(root.get("lastName")), "%" + lastNameContains.toLowerCase() + "%")
                : null);
    }
}