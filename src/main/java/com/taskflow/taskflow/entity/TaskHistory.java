package com.taskflow.taskflow.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

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
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    private Date changedOn;
    private String previousStatus;
    private String newStatus;

    @ManyToOne
    @JoinColumn(name = "taskID", nullable = false)
    private Task task;
}
