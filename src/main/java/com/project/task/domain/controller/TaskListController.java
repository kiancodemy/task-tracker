package com.project.task.domain.controller;
import com.project.task.domain.dto.TaskListDto;
import com.project.task.domain.entities.TaskList;
import com.project.task.domain.mapper.ListMapper.TaskListMapperImpl;
import com.project.task.domain.request.CreateList;
import com.project.task.domain.response.ApiResponse;
import com.project.task.domain.services.Tasklist.TaskListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/tasklist")
@RequiredArgsConstructor
public class TaskListController {
    private final TaskListService taskListService;
    private final TaskListMapperImpl taskListMapper;

    @GetMapping("/allTaskLists")
    public ResponseEntity<ApiResponse> findAllTasks() {
        try {
            List<TaskList> AllLists = taskListService.ListAllTaskLists();
            if (!AllLists.isEmpty()) {
                List<TaskListDto> AllTaskList = AllLists.stream().map(taskListMapper::maptoTaskListDto).toList();
                return ResponseEntity.ok().body(new ApiResponse("sucessfully fetched", AllTaskList));
            } else {
                return ResponseEntity.ok().body(new ApiResponse("sucessfully fetched", AllLists));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("failed to fetched", null));
        }
    }

    @PostMapping("/createTaskList")
    public ResponseEntity<ApiResponse> createTaskList(@RequestBody CreateList createList) {
        try {
            TaskList create=taskListService.createList(createList);
            return ResponseEntity.ok().body(new ApiResponse("sucessfully created", create));
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("failed to created", null));
        }
    }
    @GetMapping("/getById/{id}")
    public ResponseEntity<ApiResponse> createTaskList(@PathVariable UUID id) {
        try {
            TaskList create=taskListService.getTaskListById(id);
            TaskListDto taskListDto=taskListMapper.maptoTaskListDto(create);
            return ResponseEntity.ok().body(new ApiResponse("sucessfully created",taskListDto));
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("failed to created", null));
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateTaskList(@RequestBody CreateList createList,@PathVariable UUID id) {
        try {
            TaskList create=taskListService.UpateTasklist(id,createList);
            TaskListDto taskListDto=taskListMapper.maptoTaskListDto(create);
            return ResponseEntity.ok().body(new ApiResponse("sucessfully created",taskListDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("failed to created", null));
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteTaskList(@PathVariable UUID id) {
        try {
            taskListService.DeleteById(id);
            return ResponseEntity.ok().body(new ApiResponse("sucessfully created",null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("failed to created", null));
        }
    }

}
