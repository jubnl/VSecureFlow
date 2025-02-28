package ch.jubnl.vsecureflow.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@ToString(callSuper = true)
public class SecurityRole extends BaseEntity implements Serializable, Cloneable {
    private String name;

    @OneToMany(mappedBy = "securityRole")
    @ToString.Exclude
    private Set<SecurityRoleRight> roleRights = new HashSet<>();

    @OneToMany(mappedBy = "securityGroup")
    @ToString.Exclude
    private Set<SecurityGroupRole> groupRoles = new HashSet<>();

    @Override
    public SecurityRole clone() throws CloneNotSupportedException {
        SecurityRole cloned = (SecurityRole) super.clone();
        cloned.roleRights = new HashSet<>(this.roleRights);
        cloned.groupRoles = new HashSet<>(this.groupRoles);
        return cloned;
    }
}
