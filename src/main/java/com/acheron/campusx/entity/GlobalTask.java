package com.acheron.campusx.entity;

import com.acheron.campusx.security.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Builder
@Table(name = "global_task")
public class GlobalTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @ManyToOne
    @JoinColumn(name = "teacher")
    private User teacher;
    @ManyToOne
    @JoinColumn(name = "students_groups")
    private Group group;
    @OneToMany(mappedBy = "test")
    private List<Task> tasks;
    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;
}
