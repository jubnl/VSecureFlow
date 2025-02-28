package ch.jubnl.vsecureflow.backend.service;

import ch.jubnl.vsecureflow.backend.dto.TaskDto;
import ch.jubnl.vsecureflow.backend.entity.Task;
import ch.jubnl.vsecureflow.backend.mapper.TaskMapper;
import ch.jubnl.vsecureflow.backend.repository.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskService extends BaseService<Task, TaskDto, TaskRepository, TaskMapper> {
    public TaskService(TaskRepository repository, TaskMapper mapper) {
        super(repository, mapper);
    }
}
