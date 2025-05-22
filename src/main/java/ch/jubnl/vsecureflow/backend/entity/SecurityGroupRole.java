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
@Table(name = "security_group_roles")
@Getter
@Setter
@ToString(callSuper = true)
@Audited
public class SecurityGroupRole extends BaseEntity implements Serializable, Cloneable {
    @ManyToOne
    @JoinColumn(name = "security_group_id", nullable = false)
    private SecurityGroup securityGroup;

    @ManyToOne
    @JoinColumn(name = "security_role_id", nullable = false)
    private SecurityRole securityRole;

    @Override
    public SecurityGroupRole clone() throws CloneNotSupportedException {
        SecurityGroupRole clone = (SecurityGroupRole) super.clone();
        clone.setSecurityGroup(securityGroup.clone());
        clone.setSecurityRole(securityRole.clone());
        return clone;
    }
}
