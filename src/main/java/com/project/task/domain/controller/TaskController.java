package com.project.task.domain.controller;

import com.project.task.domain.dto.TaskDto;
import com.project.task.domain.dto.TaskListDto;
import com.project.task.domain.entities.Task;
import com.project.task.domain.entities.TaskList;
import com.project.task.domain.mapper.Taskmapper.TaskMapperImpl;
import com.project.task.domain.request.CreateTask;
import com.project.task.domain.response.ApiResponse;
import com.project.task.domain.services.task.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;
    private final TaskMapperImpl taskMapper;


    @GetMapping("/allTaskByTaskId/{id}")
    public ResponseEntity<ApiResponse> findAllTaskByTaskListId(@PathVariable UUID id) {
        try{
            List<Task> task=taskService.findByTaskListId(id);
            List<TaskDto> taskDto=task.stream().map(taskMapper::maptoTaskDto).toList();
            return ResponseEntity.ok().body(new ApiResponse("success",taskDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("failed to fetched", null));
        }
    }

    @GetMapping("/findByTaskListIdAndId/{listId}/{taskId}")
    public ResponseEntity<ApiResponse> findByTaskListIdAndId(@PathVariable UUID listId,@PathVariable UUID taskId) {
        try{
            Task tasks=taskService.findByTaskListIdAndId(listId,taskId);
            TaskDto taskDto=taskMapper.maptoTaskDto(tasks);
            return ResponseEntity.ok().body(new ApiResponse("success",taskDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("failed to fetched", null));
        }
    }

    @PostMapping("/createTask/{id}")
    public ResponseEntity<ApiResponse> createTsk(@PathVariable UUID id,@RequestBody @Valid CreateTask createTask) {
        try{
            Task tasks=taskService.createTask(id,createTask);
            TaskDto taskDto=taskMapper.maptoTaskDto(tasks);
            return ResponseEntity.ok().body(new ApiResponse("success",taskDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("failed to fetched", null));
        }
    }



}
