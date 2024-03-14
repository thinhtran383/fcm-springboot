package com.example.fcmspringboot.JWT.Config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    @Autowired
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable); // spring 6.1.x
        http.sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//        http.authorizeRequests()
//                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//                .requestMatchers("/api/v1/auth/").permitAll()
//                .requestMatchers("/demo/").hasAuthority("ROLE_USER");


        http.authorizeHttpRequests(expressionInterceptUrlRegistry ->
                expressionInterceptUrlRegistry.requestMatchers("/api/v1/auth/login").permitAll()
                        .requestMatchers("/demo/test").hasAnyAuthority("ROLE_USER")
                        .anyRequest().authenticated()).authenticationProvider(authenticationProvider)
        ;
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();

    }

}
