package com.project.task.domain.Errors;

public class TaskListNotFoundError extends RuntimeException {
    public TaskListNotFoundError(String message) {
        super(message);
    }

}
