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
public class Token {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    private String tokenType;
    private Date expiryDate;

    @ManyToOne
    @JoinColumn(name = "userID", nullable = false)
    private User user;
}
