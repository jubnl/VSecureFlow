package ch.jubnl.vsecureflow.backend.dto;

import ch.jubnl.vsecureflow.backend.entity.Task;
import ch.jubnl.vsecureflow.backend.enums.TaskPriority;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link Task}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TaskDto extends BaseDto implements Serializable {
    private String title;
    private String description;
    private LocalDateTime deadline;
    private UserDto owner;
    private TaskPriority priority;
}