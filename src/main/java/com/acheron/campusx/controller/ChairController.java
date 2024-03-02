package com.acheron.campusx.controller;

import com.acheron.campusx.entity.Chair;
import com.acheron.campusx.security.repository.ChairRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v2")
@RequiredArgsConstructor
public class ChairController {
    private final ChairRepository chairRepository;
    @GetMapping("/findAllChairs")
    public List<Chair> findAllChairs(){
        return chairRepository.findAll();
    }
}
