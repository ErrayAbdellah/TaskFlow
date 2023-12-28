package com.taskflow.taskflow.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.UUID;

@Entity @Builder
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@ToString
public class Tag {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    private String tagName;

    @ManyToMany(mappedBy = "tags")
    private List<Task> tasks;
}