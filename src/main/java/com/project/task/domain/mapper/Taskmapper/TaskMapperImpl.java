package com.project.task.domain.mapper.Taskmapper;
import com.project.task.domain.dto.TaskDto;
import com.project.task.domain.entities.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapperImpl implements TaskMapper {

    @Override
    public TaskDto maptoTaskDto(Task task) {
        return new TaskDto(task.getId(),task.getTitle(),task.getDescription(),task.getDueDate(),task.getStatus(),task.getTaskPriority());
    }
}
