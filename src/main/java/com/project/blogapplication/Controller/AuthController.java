package com.project.blogapplication.Controller;

import com.project.blogapplication.Payload.JwtAuthDto;
import com.project.blogapplication.Payload.LoginDto;
import com.project.blogapplication.Payload.signUpDto;
import com.project.blogapplication.Service.AuthService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Tag(
        name = "CRUD REST APIs for Authentication in Blog Application"
)
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

    @SecurityRequirement(
            name = "Bearer Authentication"
    )
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping({"/register","/signup"})
    public ResponseEntity<String> signUp(@RequestBody signUpDto signUpDto ){
        String response=authService.signUp(signUpDto);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }
}
