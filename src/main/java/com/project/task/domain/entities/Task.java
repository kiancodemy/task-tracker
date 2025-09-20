package com.project.task.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor

@Getter
@Setter
@ToString
@EqualsAndHashCode

@Table(name="tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID
    )
    @Column(name="id",nullable = false,updatable = false)
    private UUID id;

    @Column(name="title",nullable = false)
    private String title;

    @Column(name="description")
    private String description;

    @Column(name="due_date")
    private LocalDateTime dueDate;

    @Column(name="status",nullable = false)
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Column(name="priority",nullable = false)
    @Enumerated(EnumType.STRING)
    private TaskPriority taskPriority;

    @Column(name="created",nullable = false)
    private LocalDateTime created;


    @Column(name="upddated",nullable = false)
    private LocalDateTime updated;


    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="task-list-id",referencedColumnName = "id")
    private TaskList tasklist;


}
