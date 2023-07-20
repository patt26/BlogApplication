package com.project.blogapplication.Controller;

import com.project.blogapplication.Payload.JwtAuthDto;
import com.project.blogapplication.Payload.LoginDto;
import com.project.blogapplication.Payload.signUpDto;
import com.project.blogapplication.Service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping({"/login","/signin"})
    public ResponseEntity< JwtAuthDto> Login(@RequestBody LoginDto loginDto){
       String token= authService.Login(loginDto);
        JwtAuthDto jwtAuthDto=new JwtAuthDto();
        jwtAuthDto.setToken(token);
       return ResponseEntity.ok(jwtAuthDto);
    }

    @PostMapping({"/register","/signup"})
    public ResponseEntity<String> signUp(@RequestBody signUpDto signUpDto ){
        String response=authService.signUp(signUpDto);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }
}
