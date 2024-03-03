package com.acheron.campusx.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTaskDto {
    private String question;
    private String answ1;
    private String answ2;
    private String answ3;
    private String answ4;
    private Integer trueNumber;
}
