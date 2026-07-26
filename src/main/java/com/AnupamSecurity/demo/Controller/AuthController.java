package com.AnupamSecurity.demo.Controller;

import com.AnupamSecurity.demo.DO.Request.LoginDTO;
import com.AnupamSecurity.demo.DO.Request.SignUpDto;
import com.AnupamSecurity.demo.DO.Response.UserDto;
import com.AnupamSecurity.demo.Service.AuthService;
import com.AnupamSecurity.demo.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    // I will use user service
    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody SignUpDto signUpDto) {
            UserDto userDto = userService.signUp(signUpDto);
            return ResponseEntity.ok(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
       String token= authService.login(loginDTO);
       return ResponseEntity.ok(token);
    }
}
