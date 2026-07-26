package com.AnupamSecurity.demo.Service;

import com.AnupamSecurity.demo.DO.Request.LoginDTO;
import com.AnupamSecurity.demo.Entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private  final JwtService jwtService;

    public  String login(LoginDTO loginDTO) {
        Authentication authentication=authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.email(), loginDTO.password())
        );
        User user=(User) authentication.getPrincipal();
        String token= jwtService.generateToken(user);
        return token;
    }
}
