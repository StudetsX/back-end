package com.acheron.campusx.controller;

import com.acheron.campusx.entity.Group;
import com.acheron.campusx.security.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v2")
@RequiredArgsConstructor
public class GroupController {
    private final GroupRepository groupRepository;
    @GetMapping("/findAllGroups")
    public List<Group> findAllGroups(){
        return groupRepository.findAll();
    }
}
