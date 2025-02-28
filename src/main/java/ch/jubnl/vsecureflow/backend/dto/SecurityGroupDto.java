package ch.jubnl.vsecureflow.backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link ch.jubnl.vsecureflow.backend.entity.SecurityGroup}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SecurityGroupDto extends BaseDto implements Serializable {
    private String name;
    private Set<SecurityRoleDto> roles;
    private Set<SecurityUserDto> users;
}