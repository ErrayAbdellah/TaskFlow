package com.taskflow.taskflow.service;

import com.taskflow.taskflow.dto.TaskDTO;
import com.taskflow.taskflow.enums.Status;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface ITaskService {
    void saveTask(TaskDTO taskDTO);
    void updateTaskStatus(UUID taskId, Status newStatus);
    void deleteTask(UUID taskId, UUID userId);
}
