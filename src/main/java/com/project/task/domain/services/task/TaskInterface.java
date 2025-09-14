package com.project.task.domain.services.task;

import com.project.task.domain.entities.Task;
import com.project.task.domain.entities.TaskList;
import com.project.task.domain.request.CreateTask;

import java.util.List;
import java.util.UUID;

public interface TaskInterface {

    List<Task> findByTaskListId(UUID taskListId);
    Task findByTaskListIdAndId(UUID taskListId, UUID taskId);
    Task createTask(UUID TaskListId, CreateTask createTask);

    Task createNewTask(TaskList taskList, CreateTask createTask);
    void deleteTask(UUID taskId);
}
