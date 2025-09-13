package com.project.task.domain.entities;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode

@Table(name="task_lists")
public class TaskList {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID
    )
    @Column(name="id",nullable = false,updatable = false)
    private UUID id;

    @Column(name="title",nullable = false)
    private String title;


    @Column(name="description")
    private String description;


    @Column(name="created",nullable = false)
    private LocalDateTime created;


    @Column(name="upddated",nullable = false)
    private LocalDateTime updated;

    @OneToMany(mappedBy = "tasklist",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.EAGER)
    private List<Task> tasks=new ArrayList<>();




}
