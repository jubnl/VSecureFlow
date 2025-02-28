package ch.jubnl.vsecureflow.backend.dto;

import ch.jubnl.vsecureflow.backend.entity.SecurityGroup;
import ch.jubnl.vsecureflow.backend.entity.SecurityRole;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link ch.jubnl.vsecureflow.backend.entity.SecurityGroupRole}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SecurityGroupRoleDto  extends BaseDto implements Serializable {
    private SecurityGroup securityGroup;
    private SecurityRole securityRole;
}
