package ch.jubnl.vsecureflow.backend.repository;

import ch.jubnl.vsecureflow.backend.entity.Audit;

import java.util.List;

public interface AuditRepository extends BaseRepository<Audit, Long> {
    List<Audit> findByEntityIdAndEntityName(Long entityId, String entityName);
}