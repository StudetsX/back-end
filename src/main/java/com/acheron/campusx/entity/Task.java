package com.acheron.campusx.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Builder
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String question;
    @ManyToOne
    @JoinColumn(name = "test")
    private GlobalTask test;
    private String answ1;
    private String answ2;
    private String answ3;
    private String answ4;
    @Column(name = "true_number")
    @JsonIgnore
    private Integer trueNumber;


}
