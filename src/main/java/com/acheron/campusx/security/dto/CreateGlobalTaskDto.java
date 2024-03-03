package com.acheron.campusx.security.dto;

import com.acheron.campusx.entity.Group;
import com.acheron.campusx.security.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateGlobalTaskDto {
    private String name;
    private String description;
    private Long group;
    private List<CreateTaskDto> tasks;
    private Long subject;
}
