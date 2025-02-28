package ch.jubnl.vsecureflow.backend.entity;

import ch.jubnl.vsecureflow.backend.annotation.Auditable;
import ch.jubnl.vsecureflow.backend.enums.AuditType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Entity
@Auditable(exclude = true)
@ToString(callSuper = true)
public class Audit extends BaseEntity {
    @Column(nullable = false)
    private AuditType type;
    @Column(name = "entity_id")
    private Long entityId;
    @Column(name = "entity_name", nullable = false)
    private String entityName;
    @Column(name = "field_name", nullable = false)
    private String fieldName;
    @Column(name = "previous_value")
    private String previousValue;
    @Column(name = "new_value")
    private String newValue;
}
