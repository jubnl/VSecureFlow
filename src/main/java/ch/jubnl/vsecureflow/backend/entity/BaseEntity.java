package ch.jubnl.vsecureflow.backend.entity;


import ch.jubnl.vsecureflow.backend.annotation.Secured;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.envers.Audited;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@ToString
@Getter
@Setter
@Secured
@Audited
public abstract class BaseEntity implements Serializable, Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    @JdbcTypeCode(SqlTypes.INTEGER)
    private Long id;

    private boolean deleted = false;

    @Version
    private int version;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "created_by", nullable = false)
    private String createdBy;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "updated_by", nullable = false)
    private String updatedBy;

    @Override
    public BaseEntity clone() throws CloneNotSupportedException {
        return (BaseEntity) super.clone();
    }
}
