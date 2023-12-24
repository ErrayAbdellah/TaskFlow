package com.taskflow.taskflow.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {
    private Long taskID;
    private String title;
    private String description;
    private Date createdDate;
    private Date dueDate;
    private String status;
    private Long userID;
    private List<Long> tagIDs;
}
