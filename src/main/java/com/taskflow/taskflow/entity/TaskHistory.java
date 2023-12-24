package com.taskflow.taskflow.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;
@Entity @Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TaskHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    private Date changedOn;
    private String previousStatus;
    private String newStatus;

    @ManyToOne
    @JoinColumn(name = "taskID", nullable = false)
    private Task task;
}
