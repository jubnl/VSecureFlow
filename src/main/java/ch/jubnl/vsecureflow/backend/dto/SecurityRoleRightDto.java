package ch.jubnl.vsecureflow.backend.dto;

import ch.jubnl.vsecureflow.backend.entity.SecurityRight;
import ch.jubnl.vsecureflow.backend.entity.SecurityRole;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link ch.jubnl.vsecureflow.backend.entity.SecurityRoleRight}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SecurityRoleRightDto extends BaseDto implements Serializable {
    private SecurityRole securityRole;
    private SecurityRight securityRight;
}
