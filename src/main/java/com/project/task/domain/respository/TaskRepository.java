package com.project.task.domain.respository;
import com.project.task.domain.entities.Task;
import com.project.task.domain.request.CreateTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface TaskRepository  extends JpaRepository<Task, UUID> {

    List<Task> findByTaskListId(UUID taskListId);
    Optional<Task> findByTaskListIdAndId(UUID taskListId, UUID taskId);


}
