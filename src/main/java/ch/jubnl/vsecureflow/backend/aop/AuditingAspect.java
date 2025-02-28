package ch.jubnl.vsecureflow.backend.aop;

import ch.jubnl.vsecureflow.backend.annotation.Auditable;
import ch.jubnl.vsecureflow.backend.dto.AuditDto;
import ch.jubnl.vsecureflow.backend.entity.BaseEntity;
import ch.jubnl.vsecureflow.backend.enums.AuditType;
import ch.jubnl.vsecureflow.backend.service.AuditService;
import ch.jubnl.vsecureflow.backend.service.RepositoryResolver;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Aspect to automatically set auditing fields (createdBy, updatedBy, createdAt, updatedAt),
 * and log changes (CREATE/UPDATE/DELETE) in the Audit table.
 */
@Aspect
@Component
public class AuditingAspect {
    private final AuditService auditService;
    private final RepositoryResolver repositoryResolver;

    public AuditingAspect(AuditService auditService, RepositoryResolver repositoryResolver) {
        this.auditService = auditService;
        this.repositoryResolver = repositoryResolver;
    }

    //-------------------------------------------------------------------------
    // CREATE / UPDATE Interception
    //-------------------------------------------------------------------------

    /**
     * Intercepts 'save(...)' or 'saveAndFlush(...)' calls with a single BaseEntity argument.
     */
    @Around(
            "(execution(* org.springframework.data.jpa.repository.JpaRepository+.save(..)) && args(entity)) " +
                    "|| (execution(* org.springframework.data.jpa.repository.JpaRepository+.saveAndFlush(..)) && args(entity))"
    )
    public <Entity extends BaseEntity> Object beforeSaveOrFlush(ProceedingJoinPoint joinPoint, Entity entity) throws Throwable {
        setCreatedUpdated(entity);
        boolean isAdd = entity.getId() == null;
        Entity computedEntity = (Entity) joinPoint.proceed();
        auditSingleEntity(entity, computedEntity, isAdd);
        return computedEntity;
    }

    @Around("(execution(* org.springframework.data.jpa.repository.JpaRepository+.saveAll(..)) " +
            "|| execution(* org.springframework.data.jpa.repository.JpaRepository+.saveAllAndFlush(..))) " +
            "&& args(entities)")
    public <Entity extends BaseEntity> Object beforeSaveAllOrFlushAll(ProceedingJoinPoint joinPoint, Iterable<Entity> entities) throws Throwable {
        // Capture the pre-save state by cloning each entity.
        List<Entity> originalEntities = new ArrayList<>();
        for (Entity entity : entities) {
            originalEntities.add(cloneEntity(entity)); // Ensure cloneEntity does a deep copy.
            setCreatedUpdated(entity);
        }

        // Save the entities.
        Object result = joinPoint.proceed();

        // Audit each entity with its previous and new state.
        if (result instanceof Iterable) {
            Iterator<Entity> originalIterator = originalEntities.iterator();
            for (Entity savedEntity : (Iterable<Entity>) result) {
                Entity originalEntity = originalIterator.hasNext() ? originalIterator.next() : null;
                auditSingleEntity(originalEntity, savedEntity, originalEntity.getId() == null);
            }
        }
        return result;
    }

    private <Entity extends BaseEntity> Entity cloneEntity(Entity entity) {
        try {
            return (Entity) entity.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Cloning not supported for " + entity.getClass().getName(), e);
        }
    }

    private <Entity extends BaseEntity> void setCreatedUpdated(Entity entity) {
        LocalDateTime now = LocalDateTime.now();
        String currentEmail = getCurrentEmail();
        if (entity.getId() == null) {
            entity.setCreatedAt(now);
            entity.setCreatedBy(currentEmail);
        }
        entity.setUpdatedAt(now);
        entity.setUpdatedBy(currentEmail);
    }

    private String getCurrentEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return "system@example.com";
        }
        return authentication.getName();
    }

    private boolean isAuditable(BaseEntity entity) {
        Class<? extends BaseEntity> entityClass = entity.getClass();
        if (!entityClass.isAnnotationPresent(Auditable.class)) {
            return false;
        }
        return !entityClass.getAnnotation(Auditable.class).exclude();
    }

    /**
     * Distinguishes between CREATE vs. UPDATE by checking if entity.getId() == null.
     */
    private <Entity extends BaseEntity> List<AuditDto> auditSingleEntity(Entity entity, Entity computedEntity, boolean addMode) {
        if (!isAuditable(entity)) {
            return List.of();
        }

        String currentUserEmail = getCurrentEmail();

        // Log CREATE vs. UPDATE in Audit table
        AuditType auditType = addMode ? AuditType.CREATE : AuditType.UPDATE;
//        Optional<Entity> oldValue;
//        if (auditType == AuditType.UPDATE) {
//            BaseRepository<Entity, Long> repository = repositoryResolver.getRepository((Class<Entity>) entity.getClass());
//            oldValue = repository.findById(entity.getId());
//        } else {
//            oldValue = Optional.empty();
//        }
        return auditService.logAuditRecord(currentUserEmail, auditType, computedEntity, entity);
    }
}
