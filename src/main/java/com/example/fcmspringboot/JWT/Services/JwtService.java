package com.example.fcmspringboot.JWT.Services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.fcmspringboot.JWT.Entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Service
public class JwtService {
    @Value("${jwt.secret_key}")
    private String Secret_key;

    public String generateToken(User user, Collection<SimpleGrantedAuthority> authorities){
        Algorithm algorithm = Algorithm.HMAC256(Secret_key.getBytes());
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 50 * 60 * 1000))
                .withClaim("roles", authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);
    } // access token

    public String generateRefreshToken(User user, Collection<SimpleGrantedAuthority> authorities){
        Algorithm algorithm = Algorithm.HMAC256(Secret_key.getBytes());
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 50 * 60 * 1000))
                .sign(algorithm);
    }
}
