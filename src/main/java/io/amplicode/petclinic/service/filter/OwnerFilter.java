package io.amplicode.petclinic.service.filter;

import io.amplicode.petclinic.domain.Owner;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public record OwnerFilter(String firstNameContains, String lastNameContains) {
    public Specification<Owner> toSpecification() {
        return Specification.where(firstNameContainsSpec())
                .or(lastNameContainsSpec());
    }

    private Specification<Owner> firstNameContainsSpec() {
        return ((root, query, cb) -> StringUtils.hasText(firstNameContains)
                ? cb.like(cb.lower(root.get("firstName")), "%" + firstNameContains.toLowerCase() + "%")
                : null);
    }

    private Specification<Owner> lastNameContainsSpec() {
        return ((root, query, cb) -> StringUtils.hasText(lastNameContains)
                ? cb.like(cb.lower(root.get("lastName")), "%" + lastNameContains.toLowerCase() + "%")
                : null);
    }
}