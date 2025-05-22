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
@Table(name = "security_user_groups")
@Getter
@Setter
@ToString(callSuper = true)
@Audited
public class SecurityUserGroup extends BaseEntity implements Serializable, Cloneable {
    @ManyToOne
    @JoinColumn(name = "security_group_id", nullable = false)
    private SecurityGroup securityGroup;

    @ManyToOne
    @JoinColumn(name = "security_user_id", nullable = false)
    private SecurityUser securityUser;

    @Override
    public SecurityUserGroup clone() throws CloneNotSupportedException {
        return (SecurityUserGroup) super.clone();
    }
}
