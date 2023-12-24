package com.taskflow.taskflow.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskHistoryDTO {
    private Long historyID;
    private Date changedOn;
    private String previousStatus;
    private String newStatus;
    private Long taskID;
}
