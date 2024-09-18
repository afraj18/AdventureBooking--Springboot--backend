package com.example.AZServiceBookSystem.services.authentication;

import com.example.AZServiceBookSystem.dto.SignupRequestDto;
import com.example.AZServiceBookSystem.dto.UserDto;

public interface AuthService {
    public UserDto signupClient(SignupRequestDto signupRequestDto);
    public UserDto signupCompany(SignupRequestDto signupRequestDto);

    Boolean presentByEmail(String email);
}
