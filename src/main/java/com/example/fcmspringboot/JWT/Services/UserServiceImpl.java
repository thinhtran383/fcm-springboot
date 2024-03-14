package com.example.fcmspringboot.JWT.Services;

import com.example.fcmspringboot.JWT.Entity.Role;
import com.example.fcmspringboot.JWT.Entity.User;
import com.example.fcmspringboot.JWT.Repositories.RoleRepository;
import com.example.fcmspringboot.JWT.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j // create log

public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void addToUser(String username, String rolename) {
        User user = userRepository.findByUsername(username).get();
        Role role = roleRepository.findByName(rolename);

        user.getRoles().add(role);
    }
}
