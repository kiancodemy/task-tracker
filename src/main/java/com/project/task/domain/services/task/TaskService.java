package com.project.task.domain.services.task;
import com.project.task.domain.Errors.TaskListNotFoundError;
import com.project.task.domain.entities.Task;
import com.project.task.domain.entities.TaskList;
import com.project.task.domain.request.CreateTask;
import com.project.task.domain.respository.TaskListRepository;
import com.project.task.domain.respository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskService implements TaskInterface  {
    private final TaskRepository taskRepository;
    private final TaskListRepository taskListRepository;

    @Override
    public List<Task> findByTaskListId(UUID taskListId) {
        return taskRepository.findByTaskListId(taskListId);
    }

    @Override
    public Task findByTaskListIdAndId(UUID taskListId, UUID taskId) {
        return taskRepository.findByTaskListIdAndId(taskListId, taskId).orElseThrow(()->new TaskListNotFoundError("task not found"));
    }

    @Override
    public Task createTask(UUID TaskListId, CreateTask createTask) {
        TaskList taskList= taskListRepository.findById(TaskListId).orElseThrow(()->new TaskListNotFoundError("NOT FOUND "));
        Task createNewTask=createNewTask(taskList,createTask);
        return taskRepository.save(createNewTask);
    }

    @Override
    public Task createNewTask(TaskList taskList, CreateTask createTask) {
        Task task = new Task();
        LocalDateTime localDateTime = LocalDateTime.now();
        task.setCreated(localDateTime);
        task.setUpdated(localDateTime);
        task.setTaskList(taskList);
        task.setStatus(createTask.status());
        task.setDueDate(createTask.dueDate());
        task.setDescription(createTask.description());
        return taskRepository.save(task);

    }

    @Override
    public void deleteTask(UUID taskId) {
        taskRepository.deleteById(taskId);
    }
}
