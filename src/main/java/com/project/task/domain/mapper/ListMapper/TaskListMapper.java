package com.project.task.domain.mapper.ListMapper;

import com.project.task.domain.dto.TaskListDto;
import com.project.task.domain.entities.TaskList;

public interface TaskListMapper {
    TaskListDto maptoTaskListDto(TaskList taskList);
}
