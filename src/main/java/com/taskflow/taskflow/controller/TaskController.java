package com.taskflow.taskflow.controller;

import com.taskflow.taskflow.dto.TaskDTO;
import com.taskflow.taskflow.service.ITaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/api-task")
public class TaskController {
    private final ITaskService  taskService ;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TaskDTO> saveTsk(@RequestBody TaskDTO taskDTO){
        taskService.saveTask(taskDTO);
        return ResponseEntity.ok(taskDTO);
    }
}
