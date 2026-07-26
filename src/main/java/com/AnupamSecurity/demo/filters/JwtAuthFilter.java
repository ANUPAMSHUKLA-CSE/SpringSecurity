package com.AnupamSecurity.demo.filters;

import com.AnupamSecurity.demo.Entities.User;
import com.AnupamSecurity.demo.Repository.UserRepository;
import com.AnupamSecurity.demo.Service.JwtService;
import com.AnupamSecurity.demo.Service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private  final JwtService jwtService;
    private final UserService userService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String requestTokenHeader = request.getHeader("Authorization");
        if (requestTokenHeader == null ||!requestTokenHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request,response);
            return;
        }
        String token=requestTokenHeader.split("Bearer ")[1];
        Long userId=jwtService.getUserIdFromToken(token);

        if(userId!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            User user=userService.getUserById(userId);

            UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(user,null,null);
            //more info I have to pass then it will contain info like ip address and so on.........rate limiting and more details
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request,response);
    }
}
