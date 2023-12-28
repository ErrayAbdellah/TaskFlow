package com.taskflow.taskflow.service;

import com.taskflow.taskflow.dto.TaskDTO;
import org.springframework.stereotype.Service;

@Service
public interface ITaskService {
    void saveTask(TaskDTO taskDTO);
}
