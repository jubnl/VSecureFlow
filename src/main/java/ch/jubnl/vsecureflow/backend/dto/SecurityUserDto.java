package ch.jubnl.vsecureflow.backend.dto;

import ch.jubnl.vsecureflow.backend.entity.SecurityGroup;
import ch.jubnl.vsecureflow.backend.entity.SecurityUser;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link SecurityUser}
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SecurityUserDto extends BaseDto implements Serializable {
    private String username;
    private String password;
    private Set<SecurityGroup> groups;
}