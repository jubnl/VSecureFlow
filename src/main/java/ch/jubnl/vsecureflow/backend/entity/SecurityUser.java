package ch.jubnl.vsecureflow.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.envers.Audited;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@ToString(callSuper = true)
@Audited
public class SecurityUser extends BaseEntity implements Serializable, Cloneable {
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "securityUser")
    @ToString.Exclude
    private Set<SecurityUserGroup> userGroups = new HashSet<>();

    @Override
    public SecurityUser clone() throws CloneNotSupportedException {
        SecurityUser cloned = (SecurityUser) super.clone();
        cloned.userGroups = new HashSet<>(this.userGroups);
        return cloned;
    }
}
