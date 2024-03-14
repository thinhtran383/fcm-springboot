package com.example.fcmspringboot.JWT.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "Users", schema = "Secures")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId", nullable = false)
    private Integer id;

    @Column(name = "username", length = 100)
    private String username;

    @Column(name = "password", length = 100)
    private String password;

    @ManyToMany
    @JoinTable(name = "users_role", joinColumns = @JoinColumn(name = "Users_Id"), inverseJoinColumns = @JoinColumn(name = "Roles_Id"))
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        roles.stream().forEach(i ->authorities.add(new SimpleGrantedAuthority(i.getName())));
        return List.of(new SimpleGrantedAuthority(authorities.toString()));
    }


    @Override
    public String getUsername(){
        return this.username;
    }

    @Override
    public String getPassword(){
        return this.password;
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public User(Integer id, String username, String password, Set<Role> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }
}