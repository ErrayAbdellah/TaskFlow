package com.taskflow.taskflow.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {
    private UUID id;

    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
    private String title;

    @Size(max = 500, message = "Description cannot be longer than 500 characters")
    private String description;

    @NotNull(message = "Created date is required")
    @FutureOrPresent(message = "Created date must be present or future time")
    private Date createdDate;

    @NotNull(message = "Due date is required")
    @FutureOrPresent(message = "Due date must be in the future")
    private Date dueDate;

    @NotBlank(message = "Status is required")
    private String status;

    private UUID userID;

    private List<String> tagNames;
}