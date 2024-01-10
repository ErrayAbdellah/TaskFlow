package com.taskflow.taskflow.mapper;

import com.taskflow.taskflow.dto.TaskDTO;
import com.taskflow.taskflow.entity.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    Task toTask(TaskDTO dto);
    TaskDTO toTDO(Task task);
}
