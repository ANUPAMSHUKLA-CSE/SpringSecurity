package com.AnupamSecurity.demo.Entities;


import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true)
    private String email;
    private String password;



    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }
    // TODO: ROLES - USER CAN ADD ROLES IN SPRING THAT IS CALLED AS AUTHORITY
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }
}

