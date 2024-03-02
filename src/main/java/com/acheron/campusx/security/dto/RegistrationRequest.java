package com.acheron.campusx.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public RegistrationRequest(String firstName, String lastName, String email, String password, Integer chair, String role, MultipartFile image) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.chair = chair;
        this.role = role;
        this.image = image;
    }

    private Integer chair;
    private Long group;
    private String role;
    private MultipartFile image;


    public RegistrationRequest(String firstName, String lastName, String email, String password, Long group,  String role, MultipartFile image) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.group = group;
        this.role = role;
        this.image = image;
    }
}
