package com.project.task.domain.mapper.Taskmapper;
import com.project.task.domain.dto.TaskDto;
import com.project.task.domain.entities.Task;

public interface TaskMapper {

    TaskDto maptoTaskDto(Task task);
}
