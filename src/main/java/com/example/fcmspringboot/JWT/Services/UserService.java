package com.example.fcmspringboot.JWT.Services;

import com.example.fcmspringboot.JWT.Entity.Role;
import com.example.fcmspringboot.JWT.Entity.User;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addToUser(String username, String rolename);
}
