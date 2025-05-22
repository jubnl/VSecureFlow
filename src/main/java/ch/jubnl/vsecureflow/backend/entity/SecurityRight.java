package ch.jubnl.vsecureflow.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
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
public class SecurityRight extends BaseEntity implements Serializable, Cloneable {
    private String name;

    @ManyToMany(mappedBy = "roleRights")
    @ToString.Exclude
    private Set<SecurityRole> roles = new HashSet<>();

    @Override
    public SecurityRight clone() throws CloneNotSupportedException {
        SecurityRight cloned = (SecurityRight) super.clone();
        // Shallow copy of the Set so the cloned entity has a separate collection
        cloned.roles = new HashSet<>(this.roles);
        return cloned;
    }
}
