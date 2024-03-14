package com.example.fcmspringboot.JWT.Controller;

import com.example.fcmspringboot.JWT.Auth.AuthenticationRequest;
import com.example.fcmspringboot.JWT.Auth.AuthenticationResponse;
import com.example.fcmspringboot.JWT.Services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    @Autowired
    private final AuthenticationService authenticationService;
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest){
        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }
}
