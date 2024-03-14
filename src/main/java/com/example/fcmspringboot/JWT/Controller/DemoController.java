package com.example.fcmspringboot.JWT.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {
    @GetMapping("/test")
    public String hello(){
        return "Hello from demo";
    }
}
