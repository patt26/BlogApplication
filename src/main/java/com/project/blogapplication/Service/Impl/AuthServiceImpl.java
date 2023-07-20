package com.project.blogapplication.Service.Impl;

import com.project.blogapplication.Exceptions.BlogApiException;
import com.project.blogapplication.Model.Role;
import com.project.blogapplication.Model.User;
import com.project.blogapplication.Payload.LoginDto;
import com.project.blogapplication.Payload.signUpDto;
import com.project.blogapplication.Repository.RoleRepository;
import com.project.blogapplication.Repository.UserRepository;
import com.project.blogapplication.Security.JwtTokenProvider;
import com.project.blogapplication.Service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthServiceImpl implements AuthService {
   private final AuthenticationManager authenticationManager;
   private final UserRepository userRepository;
   private final RoleRepository roleRepository;
   private final PasswordEncoder passwordEncoder;
   private final JwtTokenProvider jwtTokenProvider;

    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder,
                           JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public String Login(LoginDto loginDto) {
       Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(),loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token=jwtTokenProvider.generateToken(authentication);

        return token;
    }

    @Override
    public String signUp(signUpDto signUpDto) {
        if(userRepository.existsByUsername(signUpDto.getUsername())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"Username is already registered");
        }
        if(userRepository.existsByEmail(signUpDto.getEmail())){
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"Email is already exists");
        }
        User user=new User();
        user.setName(signUpDto.getName());
        user.setEmail(signUpDto.getEmail());
        user.setUsername(signUpDto.getUsername());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        List<Role> roles=new ArrayList<>();
        Role userRole=roleRepository.findByRole("ROLE_USER").get();
        roles.add(userRole);
        user.setRoles(roles);
        userRepository.save(user);

        return "User Registered Successfully";

    }
}
