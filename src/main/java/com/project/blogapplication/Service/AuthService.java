package com.project.blogapplication.Service;

import com.project.blogapplication.Payload.LoginDto;
import com.project.blogapplication.Payload.signUpDto;

public interface AuthService {
    String Login(LoginDto loginDto);

    String signUp(signUpDto signUpDto);
}
