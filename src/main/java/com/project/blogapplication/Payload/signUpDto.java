package com.project.blogapplication.Payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class signUpDto {
    private String name;
    private String username;
    private String email;
    private String password;
    private String role;
}
