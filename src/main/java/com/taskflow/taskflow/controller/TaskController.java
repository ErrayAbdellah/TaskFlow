package com.taskflow.taskflow.controller;

import com.taskflow.taskflow.dto.TaskDTO;
import com.taskflow.taskflow.enums.Status;
import com.taskflow.taskflow.service.ITaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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

    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateTaskStatus(@PathVariable UUID id, @RequestParam Status status) {
        try {
            taskService.updateTaskStatus(id, status);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
