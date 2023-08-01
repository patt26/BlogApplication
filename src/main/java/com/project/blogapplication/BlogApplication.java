package com.project.blogapplication;

import com.project.blogapplication.Config.SecurityConfig;
import com.project.blogapplication.Payload.LoginDto;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "SpringBoot Blog Application with REST API",
                description = "Full backend application using REST API",
                contact = @Contact(
                        name = "Pratik Kharche",
                        email = "kharchepratik123@gmail.com"
                )
        )
)
public class BlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }

}
