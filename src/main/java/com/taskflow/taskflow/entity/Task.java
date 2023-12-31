package com.taskflow.taskflow.entity;

import com.taskflow.taskflow.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.List;
import java.util.UUID;
@Entity @Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class Task {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    private String title;
    private String description;
    private Date createdDate;
    private Date dueDate;
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "userID", nullable = false)
    private User user;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private List<TaskHistory> taskHistories;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "task_tags",
            joinColumns = @JoinColumn(name = "taskID"),
            inverseJoinColumns = @JoinColumn(name = "tagID"))
    private List<Tag> tags;
}
