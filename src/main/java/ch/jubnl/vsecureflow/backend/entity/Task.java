package ch.jubnl.vsecureflow.backend.entity;

import ch.jubnl.vsecureflow.backend.enums.TaskPriority;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@ToString(callSuper = true)
public class Task extends BaseEntity implements Serializable, Cloneable {
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    private LocalDateTime deadline;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    private User owner;

    private TaskPriority priority;

    @Override
    public Task clone() throws CloneNotSupportedException {
        Task clone = (Task) super.clone();
        clone.setOwner(owner.clone());
        return clone;
    }
}
