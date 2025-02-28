package ch.jubnl.vsecureflow.backend.dto;

import ch.jubnl.vsecureflow.backend.entity.SecurityRole;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link ch.jubnl.vsecureflow.backend.entity.SecurityRight}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SecurityRightDto extends BaseDto implements Serializable {
    String name;
    private Set<SecurityRole> roles;
}