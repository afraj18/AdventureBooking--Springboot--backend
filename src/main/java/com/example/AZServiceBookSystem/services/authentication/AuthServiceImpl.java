package com.example.AZServiceBookSystem.services.authentication;

import com.example.AZServiceBookSystem.dto.SignupRequestDto;
import com.example.AZServiceBookSystem.dto.UserDto;
import com.example.AZServiceBookSystem.entity.User;
import com.example.AZServiceBookSystem.enums.UserRole;
import com.example.AZServiceBookSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    private UserRepository userRepository;

    public UserDto signupClient(SignupRequestDto signupRequestDto){
        User user = new User();

        user.setName(signupRequestDto.getName());
        user.setLastname(signupRequestDto.getLastname());
        user.setEmail(signupRequestDto.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequestDto.getPassword() ));
        user.setPhone(signupRequestDto.getPhone());

        user.setRole(UserRole.CLIENT);

        return userRepository.save(user).getDto();
    }

    public UserDto signupCompany(SignupRequestDto signupRequestDto){
        User user = new User();

        user.setName(signupRequestDto.getName());
        user.setEmail(signupRequestDto.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequestDto.getPassword()));
        user.setPhone(signupRequestDto.getPhone());

        user.setRole(UserRole.COMPANY);

        return userRepository.save(user).getDto();
    }

    public Boolean presentByEmail(String email){
        return userRepository.findFirstByEmail(email) != null;
    }

}
