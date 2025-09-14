package com.project.task.domain.services.Tasklist;
import com.project.task.domain.Errors.FilAll;
import com.project.task.domain.Errors.TaskListNotFoundError;
import com.project.task.domain.entities.TaskList;
import com.project.task.domain.request.CreateList;
import com.project.task.domain.respository.TaskListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskListService implements TaskListInterface {
    private final TaskListRepository taskListRepository;

    @Override
    public List<TaskList> ListAllTaskLists() {
        return taskListRepository.findAll();
    }

    @Override
    public TaskList createList(CreateList createList) {
        if(createList.title()==null){
            throw new FilAll("fill all sections");
        }
        LocalDateTime createdAt = LocalDateTime.now();
        TaskList list=new TaskList();
        list.setTitle(createList.title());
        list.setDescription(createList.description());
        list.setCreated(createdAt);
        list.setUpdated(createdAt);
        return taskListRepository.save(list);
    }

    @Override
    public TaskList getTaskListById(UUID id) {
        return taskListRepository.findById(id).orElseThrow(() -> new TaskListNotFoundError("task not available"));}

    @Override
    public TaskList UpateTasklist(UUID TaskListId, CreateList createList) {
        if(createList.title()==null || createList.description()==null){
            throw new FilAll("fill all sections");

        }
        TaskList taskList=taskListRepository.findById(TaskListId).orElseThrow(() -> new TaskListNotFoundError("task not available"));
        taskList.setTitle(createList.title());
        taskList.setDescription(createList.description());
        taskList.setUpdated(LocalDateTime.now());
        return taskListRepository.save(taskList);
    }

    @Override
    public void DeleteById(UUID id) {
        taskListRepository.findById(id).ifPresentOrElse((item)->taskListRepository.deleteById(item.getId()),()->{throw new TaskListNotFoundError("tasklist not found");});


    }


}
