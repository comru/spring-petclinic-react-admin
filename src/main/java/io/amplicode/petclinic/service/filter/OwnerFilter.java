package io.amplicode.petclinic.service.filter;

import io.amplicode.petclinic.domain.Owner;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public record OwnerFilter(String q) {
    public Specification<Owner> toSpecification() {
        return Specification.where(firstNameContainsSpec())
                .or(lastNameContainsSpec());
    }

    private Specification<Owner> firstNameContainsSpec() {
        return ((root, query, cb) -> StringUtils.hasText(q)
                ? cb.like(cb.lower(root.get("firstName")), "%" + q.toLowerCase() + "%")
                : null);
    }

    private Specification<Owner> lastNameContainsSpec() {
        return ((root, query, cb) -> StringUtils.hasText(q)
                ? cb.like(cb.lower(root.get("lastName")), "%" + q.toLowerCase() + "%")
                : null);
    }
}