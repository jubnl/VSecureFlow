package ch.jubnl.vsecureflow.backend.entity;

import ch.jubnl.vsecureflow.backend.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "app_user")
@ToString(callSuper = true)
public class User extends BaseEntity implements Serializable, Cloneable {
    @Column(name = "first_name", length = 50)
    private String firstName;

    @Column(name = "last_name", length = 50)
    private String lastName;

    @Column(nullable = false)
    @Email
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Role> roles;

    @Lob
    @Column(length = 1000000)
    private byte[] profilePicture;

    @Override
    public User clone() throws CloneNotSupportedException {
        return (User) super.clone();
    }
}
