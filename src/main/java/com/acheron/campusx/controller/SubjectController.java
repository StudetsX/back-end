package com.acheron.campusx.controller;

import com.acheron.campusx.entity.Subject;
import com.acheron.campusx.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v2")
@RequiredArgsConstructor
public class SubjectController {
    private final SubjectRepository subjectRepository;

    @GetMapping("/findAllSubjects")
    public List<Subject> findAllSubjects(){
        return subjectRepository.findAll();
    }
}
