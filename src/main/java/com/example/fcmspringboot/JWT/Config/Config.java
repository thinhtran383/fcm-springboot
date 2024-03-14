package com.example.fcmspringboot.JWT.Config;

import com.example.fcmspringboot.JWT.Entity.Role;
import com.example.fcmspringboot.JWT.Entity.User;
import com.example.fcmspringboot.JWT.Services.UserService;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Configuration
public class Config {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(UserService userService) {
        return args -> {
//            userService.saveRole(new Role(null, "ROLE_USER"));
//            userService.saveRole(new Role(null,"ROLE_MANAGER"));
//            userService.saveRole(new Role(null,"ROLE_ADMIN"));
//            userService.saveRole(new Role(null,"ROLE_SUPER_ADMIN"));
//
//            userService.saveUser(new User(null, "thinhtran383", "123", new HashSet<>()));
//            userService.saveUser(new User(null, "vanhlu3897", "123", new HashSet<>()));
//            userService.saveUser(new User(null, "tranthinh123", "123", new HashSet<>()));
//            userService.saveUser(new User(null, "thinhtran999", "123", new HashSet<>()));

            userService.addToUser("thinhtran383", "ROLE_SUPER_ADMIN");
            userService.addToUser("vanhlu3897", "ROLE_ADMIN");
            userService.addToUser("tranthinh123", "ROLE_USER");

        };
    }
}
