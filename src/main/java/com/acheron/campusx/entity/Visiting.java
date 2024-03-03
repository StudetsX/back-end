package com.acheron.campusx.entity;

import com.acheron.campusx.security.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Builder
public class Visiting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    @Column(name = "max_tasks")
    private Integer maxTask;
    @Column(name = "current_tasks")
    private Integer currentTask;



}
