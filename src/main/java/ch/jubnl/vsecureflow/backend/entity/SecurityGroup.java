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
public class SecurityGroup extends BaseEntity implements Serializable, Cloneable {
    private String name;

    @OneToMany(mappedBy = "securityGroup")
    @ToString.Exclude
    private Set<SecurityGroupRole> groupRoles = new HashSet<>();

    @OneToMany(mappedBy = "securityGroup")
    @ToString.Exclude
    private Set<SecurityUserGroup> userGroups = new HashSet<>();

    @Override
    public SecurityGroup clone() throws CloneNotSupportedException {
        return (SecurityGroup) super.clone();
    }
}
