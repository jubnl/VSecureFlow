package ch.jubnl.vsecureflow.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.envers.Audited;

import java.io.Serializable;

@Entity
@Table(name = "role_rights")
@Getter
@Setter
@ToString(callSuper = true)
@Audited
public class SecurityRoleRight extends BaseEntity implements Serializable, Cloneable {
    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private SecurityRole securityRole;

    @ManyToOne
    @JoinColumn(name = "right_id", nullable = false)
    private SecurityRight securityRight;

    @Override
    public SecurityRoleRight clone() throws CloneNotSupportedException {
        return (SecurityRoleRight) super.clone();
    }
}
