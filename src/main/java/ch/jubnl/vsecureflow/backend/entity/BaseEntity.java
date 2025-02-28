package ch.jubnl.vsecureflow.backend.entity;

import ch.jubnl.vsecureflow.backend.annotation.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@ToString
@Auditable
@Getter
@Setter
public abstract class BaseEntity implements Serializable, Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    @JdbcTypeCode(SqlTypes.INTEGER)
    private Long id;

    @Version
    @Auditable(exclude = true)
    private int version;

    @Auditable(exclude = true)
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Auditable(exclude = true)
    @Column(name = "created_by", nullable = false)
    private String createdBy;

    @Auditable(exclude = true)
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Auditable(exclude = true)
    @Column(name = "updated_by", nullable = false)
    private String updatedBy;

    @Override
    public BaseEntity clone() throws CloneNotSupportedException {
        return (BaseEntity) super.clone();
    }
}
