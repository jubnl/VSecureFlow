package ch.jubnl.vsecureflow.backend.dto;

import ch.jubnl.vsecureflow.backend.entity.Audit;
import ch.jubnl.vsecureflow.backend.enums.AuditType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * DTO for {@link Audit}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AuditDto extends BaseDto implements Serializable {
    private AuditType type;
    private Long entityId;
    private String entityName;
    private String fieldName;
    private String previousValue;
    private String newValue;
}