package com.spring.controller;

import com.spring.domain.JwtResponse;
import com.spring.domain.JwtUserRequest;
import com.spring.security.JwtTokenProvider;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.net.Authenticator;
import java.util.Arrays;
import java.util.Collections;

@RestController
@RequestMapping("auth")
public class AdminController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @PostMapping(produces = "application/json",consumes = "application/json")
    public ResponseEntity<Object> createToken(@RequestBody JwtUserRequest jwtUserRequest){
        Authentication auth =authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtUserRequest.getUserName(),jwtUserRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(auth);
        String token =jwtTokenProvider.createToken(jwtUserRequest.getUserName(),auth.getAuthorities());
        return ResponseEntity.ok(new JwtResponse(token));

    }
}
