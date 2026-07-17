package com.AnupamSecurity.demo.Service;


import com.AnupamSecurity.demo.Exception.ResourceNotFoundException;
import com.AnupamSecurity.demo.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//Our own user service i.e MyUserDetails service----for that load it by the userName
//in context of spring security we call username =email and password....
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("User with email"+username+"not found"));
    }
}
