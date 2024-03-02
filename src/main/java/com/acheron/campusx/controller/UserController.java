package com.acheron.campusx.controller;

import com.acheron.campusx.mapper.UserToDtoMapper;
import com.acheron.campusx.security.dto.UserResponseDto;
import com.acheron.campusx.security.entity.User;
import com.acheron.campusx.security.jwt.JwtUtil;
import com.acheron.campusx.security.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2")
public class UserController {
    private final UserService userService;
    private final UserToDtoMapper mapper;
    private final JwtUtil jwtUtil;

    @GetMapping("/users")
    public List<UserResponseDto> findAllUsers(){
        return userService.findAll().stream().map(mapper::map).toList();
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id, HttpServletRequest request){
        String authorization = request.getHeader("Authorization");
        if(authorization!=null && !authorization.isEmpty()){
            String username = jwtUtil.getUsername(authorization.substring(7));
            User user = userService.findByEmail(username).orElseThrow();
            if(user.getId().equals(id)){
                return ResponseEntity.ok(user);
            }
        }
        return ResponseEntity.ok(userService.findById(id).map(mapper::map).orElseThrow());
    }

}
