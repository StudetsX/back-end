package com.acheron.campusx.security.controller;

import com.acheron.campusx.security.dto.ChangeEmailDto;
import com.acheron.campusx.security.dto.LoginRequest;
import com.acheron.campusx.security.dto.RegistrationRequest;
import com.acheron.campusx.security.entity.User;
import com.acheron.campusx.security.jwt.JwtUtil;
import com.acheron.campusx.security.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class LoginController {
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    @PostMapping(value = "/api/v2/registration")
    public ResponseEntity<String> register(
            @RequestParam(name = "firstName") String firstName,
            @RequestParam(name = "lastName") String lastName,
            @RequestParam(name = "email") String email,
            @RequestParam(name = "password") String password,
            @RequestParam(name = "chair",required = false) Integer chair,
            @RequestParam(name = "group",required = false) Long group,
            @RequestParam(name = "course",required = false) Integer course,
            @RequestParam(name = "role") String role,
            @RequestParam(name = "image", required = false)MultipartFile image
    ){

        MultipartFile image1 = null;
        if(image!=null){
            image1 = image;
        }
        RegistrationRequest registrationRequest =null;
        if(group ==null  && chair!=null){
            registrationRequest= new RegistrationRequest(firstName,lastName,
                    email,password,chair,role,image1);
        }else if(group !=null && course!=null && chair==null){
            registrationRequest= new RegistrationRequest(firstName,lastName,
                    email,password,group,role,image1);
        }
        System.out.println(registrationRequest);
        User save = userService.save(registrationRequest);
        return ResponseEntity.ok(jwtUtil.generateToken(save));
    }
//    @PostMapping(value = "/api/v2/registration")
//    public ResponseEntity<String> register(@RequestParam MultipartFile image){
//        System.out.println(image);
//        return ResponseEntity.ok("asds");
//    }

    @PostMapping("/api/v2/saveImage")
    public ResponseEntity<String> saveImage(Principal principal,@RequestPart MultipartFile image){
        try{
            User user = userService.findByEmail(principal.getName()).orElseThrow();
            User save = userService.saveUserImage(user, image);
            return ResponseEntity.ok("successful");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("bad request");
        }
    }


    @PostMapping("/api/v2/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request){
        if (request.getEmail() != null) {
            try {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
            }catch (BadCredentialsException e){
                return ResponseEntity.status(403).body("Bad credentials");

            }
        }
        return ResponseEntity.ok(jwtUtil.generateToken(userService.findByEmail(request.getEmail()).orElseThrow()));
    }

    @PostMapping("/api/v2/changePassword")
    public ResponseEntity<String> changePassword(@RequestBody String password,Principal principal){
        if(principal.getName()!=null && !principal.getName().isBlank()){
            User user = userService.findByEmail(principal.getName()).orElseThrow();
            user.setPassword(password);
            User save = userService.save(user);
            return ResponseEntity.ok(jwtUtil.generateToken(save));
        }
        return ResponseEntity.status(403).body("Forbidden");
    }
    @PostMapping("/api/v2/changeEmail")
    public ResponseEntity<String> changeUsername(@RequestBody ChangeEmailDto email, Principal principal){
        if(principal.getName()!=null && !principal.getName().isBlank()){
            User user = userService.findByEmail(principal.getName()).orElseThrow();
            user.setEmail(email.getEmail());
            User save = userService.save(user);
            return ResponseEntity.ok(jwtUtil.generateToken(save));
        }
        return ResponseEntity.status(403).body("Forbidden");
    }
    @GetMapping("/asd")
    public ResponseEntity<String> login1(){
        return ResponseEntity.ok("asd");
    }
    @GetMapping("/asd1")
    public String asd(){
        return "ad";
    }
}
