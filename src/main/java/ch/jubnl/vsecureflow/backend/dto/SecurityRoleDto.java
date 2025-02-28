package ch.jubnl.vsecureflow.backend.dto;

import ch.jubnl.vsecureflow.backend.entity.SecurityGroup;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link ch.jubnl.vsecureflow.backend.entity.SecurityRole}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SecurityRoleDto extends BaseDto implements Serializable {
    private String name;
    private Set<SecurityRightDto> rights;
    private Set<SecurityGroup> groups;
}