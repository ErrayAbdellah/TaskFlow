package com.taskflow.taskflow.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskHistoryDTO {
    private UUID id;
    @NotNull(message = "Change date is required")
    private Date changedOn;

    @NotBlank(message = "Previous status is required")
    private String previousStatus;

    @NotBlank(message = "New status is required")
    private String newStatus;

    @NotNull(message = "Task ID is required")
    private UUID taskID;
}
