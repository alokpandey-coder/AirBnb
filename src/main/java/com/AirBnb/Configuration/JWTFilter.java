package com.AirBnb.Configuration;

import com.AirBnb.Model.Roles;
import com.AirBnb.Model.Users;
import com.AirBnb.Repository.UsersRepository;
import com.AirBnb.Service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JWTFilter extends OncePerRequestFilter {

    private final JWTService jwtService;
    private final UsersRepository usersRepository;

    public JWTFilter(JWTService jwtService,
                     UsersRepository usersRepository) {
        this.jwtService = jwtService;
        this.usersRepository = usersRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {


         String token = request.getHeader("Authorization");

         if(token!=null && token.startsWith("Bearer ")){

              String tokenVal = token.substring(8,token.length() - 1);
            // String tokenVal = token.substring(7);
             String username = jwtService.getUserName(tokenVal);

             Optional<Users> opUserName = usersRepository.findByUserName(username);

             if(opUserName.isPresent()){

                 Users user = opUserName.get();

                 //for mapping we have to know the roles

                     Set<SimpleGrantedAuthority> authorities = user.getRoles().stream()
                             .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRoleName()))
                             .collect(Collectors.toSet());




                 UsernamePasswordAuthenticationToken authToken
                                               = new UsernamePasswordAuthenticationToken
                                                                        (user,null, authorities);   //Collections.singleton(new SimpleGrantedAuthority())

                 authToken.setDetails(new WebAuthenticationDetails(request));
                 SecurityContextHolder.getContext().setAuthentication(authToken);
             }
         }

           filterChain.doFilter(request,response);
    }
}
