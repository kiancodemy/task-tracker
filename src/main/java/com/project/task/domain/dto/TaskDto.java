package com.project.task.domain.dto;
import com.project.task.domain.entities.TaskPriority;
import com.project.task.domain.entities.TaskStatus;
import java.time.LocalDateTime;
import java.util.UUID;

public record TaskDto(UUID id,String title, String description, LocalDateTime dueDate, TaskStatus status, TaskPriority taskPriority) {
}
