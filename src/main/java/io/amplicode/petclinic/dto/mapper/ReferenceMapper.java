package io.amplicode.petclinic.dto.mapper;

import io.amplicode.petclinic.domain.base.BaseEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.mapstruct.TargetType;
import org.springframework.stereotype.Component;

/**
 * See: <a href="https://mapstruct.org/documentation/stable/reference/html/#passing-target-type">Passing the mapping target type to custom mappers</a>
 */
@Component
@RequiredArgsConstructor
public class ReferenceMapper {

    @PersistenceContext
    private final EntityManager entityManager;

    public <T extends BaseEntity> T resolveToReference(Long id, @TargetType Class<T> entityClass) {
        return id == null ? null : entityManager.getReference(entityClass, id);
    }
}
