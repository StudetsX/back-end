package com.acheron.campusx.mapper;

import com.acheron.campusx.entity.GlobalTask;
import com.acheron.campusx.entity.Task;
import com.acheron.campusx.security.dto.CreateTaskDto;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class TaskDtoListToTaskListMapper {

    public List<Task> map(List<CreateTaskDto> dtos, GlobalTask globalTask){
        return dtos.stream().map(el->new Task(null,el.getQuestion(),globalTask, el.getAnsw1(), el.getAnsw2(), el.getAnsw3(), el.getAnsw4(), el.getTrueNumber())).toList();
    }
}
