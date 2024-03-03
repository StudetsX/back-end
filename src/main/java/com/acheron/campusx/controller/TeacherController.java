package com.acheron.campusx.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/teacher")
@RequiredArgsConstructor
public class TeacherController {

    @PostMapping("/send/{id}")
    public ResponseEntity<String> sendMessage(@PathVariable Long id){

    }
}
