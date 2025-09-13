package com.project.task.domain.mapper.ListMapper;
import com.project.task.domain.dto.TaskDto;
import com.project.task.domain.dto.TaskListDto;
import com.project.task.domain.entities.TaskList;
import com.project.task.domain.entities.TaskStatus;
import com.project.task.domain.mapper.Taskmapper.TaskMapperImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Component
@RequiredArgsConstructor
public class TaskListMapperImpl implements TaskListMapper {
    private final TaskMapperImpl taskMapperImpl;

    @Override
    public TaskListDto maptoTaskListDto(TaskList taskList) {
        List<TaskDto> taskDto= Optional.ofNullable(taskList.getTasks()).map(item->item.stream().map(taskMapperImpl::maptoTaskDto).toList()).orElse(Collections.emptyList());
        Integer count=CalculateCount(taskList);
        Double progress=CalculateProgress(taskList);
        return new TaskListDto(taskList.getId(),taskList.getTitle(),taskList.getDescription(),count,progress,taskDto);
    }

    private Integer CalculateCount(TaskList taskList){
        return taskList.getTasks() == null ? 0 : taskList.getTasks().size();
    }

    private Double CalculateProgress(TaskList taskList){
        if(taskList.getTasks()==null || taskList.getTasks().isEmpty()){
            return null;
        }
        long count=taskList.getTasks().stream().filter(task->task.getStatus()== TaskStatus.CLOSE).count();
        return (double) count/taskList.getTasks().size();
    }
}
