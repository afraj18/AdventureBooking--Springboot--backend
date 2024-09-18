package com.example.AZServiceBookSystem.dto;

import com.example.AZServiceBookSystem.enums.UserRole;
import lombok.Data;

@Data
public class SignupRequestDto {

    private Long id;
    private String email;
    private String password;
    private String name;
    private String lastname;
    private String phone;
    private UserRole role;
}
