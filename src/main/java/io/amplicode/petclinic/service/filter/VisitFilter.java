package io.amplicode.petclinic.service.filter;

import io.amplicode.petclinic.domain.Visit;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.time.LocalDate;

public record VisitFilter(LocalDate dateGte, LocalDate dateLte, String descriptionContains) {
    public Specification<Visit> toSpecification() {
        return Specification.where(dateGteSpec())
                .and(dateLteSpec())
                .and(descriptionContainsSpec());
    }

    private Specification<Visit> dateGteSpec() {
        return ((root, query, cb) -> dateGte != null
                ? cb.greaterThanOrEqualTo(root.get("date"), dateGte)
                : null);
    }

    private Specification<Visit> dateLteSpec() {
        return ((root, query, cb) -> dateLte != null
                ? cb.lessThanOrEqualTo(root.get("date"), dateLte)
                : null);
    }

    private Specification<Visit> descriptionContainsSpec() {
        return ((root, query, cb) -> StringUtils.hasText(descriptionContains)
                ? cb.like(cb.lower(root.get("description")), "%" + descriptionContains.toLowerCase() + "%")
                : null);
    }
}