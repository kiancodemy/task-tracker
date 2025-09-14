package com.project.task.domain.request;

import com.project.task.domain.entities.TaskPriority;
import com.project.task.domain.entities.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreateTask(
        @NotBlank(message = "Title is required")
        String title,
        String description,
        @NotNull(message = "Due date is required")
        LocalDateTime dueDate,
        @NotNull(message = "Task status is required")
        TaskStatus status,
        @NotNull(message = "Task priority is required")
        TaskPriority taskPriority,
        @NotNull(message = "Task list ID is required")
        UUID taskListId

) {}
