package com.project.task.domain.services.Tasklist;

import com.project.task.domain.entities.TaskList;
import com.project.task.domain.request.CreateList;

import java.util.List;
import java.util.UUID;

public interface TaskListInterface {

    List<TaskList> ListAllTaskLists();
    TaskList createList(CreateList createList);
    TaskList getTaskListById(UUID id);

    TaskList UpateTasklist(UUID TaskListId, CreateList createList);

    void DeleteById(UUID id);
}
