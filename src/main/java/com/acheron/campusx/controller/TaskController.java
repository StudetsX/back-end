package com.acheron.campusx.controller;

import com.acheron.campusx.entity.GlobalTask;
import com.acheron.campusx.entity.Rating;
import com.acheron.campusx.entity.Task;
import com.acheron.campusx.mapper.TaskDtoListToTaskListMapper;
import com.acheron.campusx.repository.*;
import com.acheron.campusx.security.dto.CreateGlobalTaskDto;
import com.acheron.campusx.security.dto.ResultDto;
import com.acheron.campusx.security.dto.TaskResponseDto;
import com.acheron.campusx.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v2")
@RequiredArgsConstructor
public class TaskController {
    private final GlobalTaskRepository globalTaskRepository;
    private final TaskRepository taskRepository;
    private final UserService userService;
    private final GroupRepository groupRepository;
    private final TaskDtoListToTaskListMapper taskDtoListToTaskListMapper;
    private final SubjectRepository subjectRepository;
    private final RatingRepository ratingRepository;

    @PostMapping("/createTest")
    public ResponseEntity<String> createGlobalTask(@RequestBody CreateGlobalTaskDto dto, Principal principal){
        try{

            GlobalTask globalTask = globalTaskRepository.save(new GlobalTask(null, dto.getName(), dto.getDescription(), userService.findByEmail(principal.getName()).orElseThrow(), groupRepository.findById(dto.getGroup()).orElseThrow(), null,subjectRepository.findById(dto.getSubject()).orElseThrow()));
            taskRepository.saveAll(taskDtoListToTaskListMapper.map(dto.getTasks(), globalTask));
            return ResponseEntity.ok("success");
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

    @PostMapping("/sendTest/{id}")
    public Integer getResult(@PathVariable Long id, @RequestBody List<ResultDto> dtos,Principal principal){
        GlobalTask globalTask = globalTaskRepository.findById(id).orElseThrow();
        List<Task> tasks = globalTask.getTasks();
        Integer result =0;

        for(int i = 0;i<tasks.size();i++){
            if(tasks.get(i).getTrueNumber().equals(dtos.get(i).getValue())){
                result++;
            }
        }
        try{
            Rating rating = ratingRepository.findByUserAndSubject(userService.findByEmail(principal.getName()).orElse(null), globalTask.getSubject()).orElseThrow();
            rating.setValue(rating.getValue()+result);
            ratingRepository.save(rating);
        }catch (Exception e){
            ratingRepository.save(new Rating(null,userService.findByEmail(principal.getName()).orElseThrow(),globalTask.getSubject(),Float.valueOf(result)));
        }
        return result;
    }

    @GetMapping("/tasks/{id}")
    public List<TaskResponseDto> findAllTasks(@PathVariable Long id){
        return globalTaskRepository.findAllBySubject(subjectRepository.findById(id).orElseThrow()).stream().map(el-> new TaskResponseDto(el.getId(),el.getName(),el.getTeacher().getFirstName()+" "+el.getTeacher().getLastName(),el.getSubject().getName(),el.getGroup().getName())).toList();
    }

    @GetMapping("/task/{id}")
    public GlobalTask findGlobalTask(@PathVariable Long id){
        return globalTaskRepository.findById(id).orElseThrow();
    }
}
