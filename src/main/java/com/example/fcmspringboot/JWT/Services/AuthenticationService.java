package com.example.fcmspringboot.JWT.Services;

import com.example.fcmspringboot.JWT.Auth.AuthenticationRequest;
import com.example.fcmspringboot.JWT.Auth.AuthenticationResponse;
import com.example.fcmspringboot.JWT.Entity.Role;
import com.example.fcmspringboot.JWT.Entity.User;
import com.example.fcmspringboot.JWT.Repositories.CustomRoleRepository;
import com.example.fcmspringboot.JWT.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final AuthenticationManager authenticationManager;
    @Autowired
    private final CustomRoleRepository customRoleRepository;
    @Autowired
    private final JwtService jwtService;

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        User user = userRepository.findByUsername(authenticationRequest.getUsername()).orElseThrow();
        List<Role> roles = null;

        if (user != null){
            roles = customRoleRepository.getRole(user);
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        Set<Role> set = new HashSet<>();

        roles.stream().forEach(c -> set.add(new Role(c.getName())));
        user.setRoles(set);
        set.stream().forEach(i -> authorities.add(new SimpleGrantedAuthority(i.getName())));

        var jwtToken = jwtService.generateToken(user, authorities);
        var jwtRefreshToken = jwtService.generateRefreshToken(user, authorities);

        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .refreshToken(jwtRefreshToken)
                .build();

    }
}
