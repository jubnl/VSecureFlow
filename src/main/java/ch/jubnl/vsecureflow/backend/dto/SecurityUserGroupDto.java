package ch.jubnl.vsecureflow.backend.dto;

import ch.jubnl.vsecureflow.backend.entity.SecurityGroup;
import ch.jubnl.vsecureflow.backend.entity.SecurityUser;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link ch.jubnl.vsecureflow.backend.entity.SecurityUserGroup}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SecurityUserGroupDto extends BaseDto implements Serializable {
    private SecurityGroup securityGroup;
    private SecurityUser securityUser;
}
