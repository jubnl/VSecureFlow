package ch.jubnl.vsecureflow.backend.dto;

import ch.jubnl.vsecureflow.backend.entity.User;
import ch.jubnl.vsecureflow.backend.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link User}
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserDto extends BaseDto implements Serializable {
    private String firstName;
    private String lastName;
    @NotNull
    @Email
    private String email;
    @NotNull
    @JsonIgnore
    private String password;
    @NotNull
    private Set<Role> roles;

    private byte[] profilePicture;
}