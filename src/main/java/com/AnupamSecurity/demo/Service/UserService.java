package com.AnupamSecurity.demo.Service;



import com.AnupamSecurity.demo.DO.Request.LoginDTO;
import com.AnupamSecurity.demo.DO.Request.SignUpDto;
import com.AnupamSecurity.demo.DO.Response.UserDto;
import com.AnupamSecurity.demo.Entities.User;
import com.AnupamSecurity.demo.Exception.ResourceNotFoundException;
import com.AnupamSecurity.demo.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Optional;

//Our own user service i.e MyUserDetails service----for that load it by the userName
//in context of spring security we call username =email and password....
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("User with email"+username+"not found"));
    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User with id"+id+"not found"));
    }
    // create a new user in a database
    public UserDto signUp(SignUpDto signUpDto) {
       Optional<User> user= userRepository.findByEmail(signUpDto.getEmail());
       if(user.isPresent()){
           throw  new BadCredentialsException("User with this email already exists"+signUpDto.getEmail());
       }
       User toCreateUser = modelMapper.map(signUpDto,User.class);
       toCreateUser.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
       User savedUser = userRepository.save(toCreateUser);
       return modelMapper.map(savedUser,UserDto.class);


    }


}
