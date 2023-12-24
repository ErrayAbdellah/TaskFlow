package com.taskflow.taskflow.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity @Builder
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@ToString
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    private String tagName;

    @ManyToMany(mappedBy = "tags")
    private List<Task> tasks;
}
