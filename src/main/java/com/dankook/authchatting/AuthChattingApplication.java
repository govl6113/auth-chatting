package com.dankook.authchatting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class AuthChattingApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthChattingApplication.class, args);
    }

}
