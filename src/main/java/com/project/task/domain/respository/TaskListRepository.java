package com.project.task.domain.respository;
import com.project.task.domain.entities.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface TaskListRepository extends JpaRepository<TaskList, UUID> {


}
