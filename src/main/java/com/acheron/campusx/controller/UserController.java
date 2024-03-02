package com.acheron.campusx.controller;

import com.acheron.campusx.security.dto.UserResponseDto;
import com.acheron.campusx.security.entity.User;
import com.acheron.campusx.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2")
public class UserController {
    private final UserService userService;

    @GetMapping("/users")
    public List<UserResponseDto> findAllUsers(){
//        return userService.findAllBy();
        return null;
    }
    @GetMapping("/user/{id}")
    public List<UserResponseDto> findById(@PathVariable Long id){
//        return userService.findById(id);
        return null;
    }
}
