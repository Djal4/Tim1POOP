package com.ldg.main.Controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.ldg.main.Models.UserDetailsImpl;

import com.ldg.main.config.JwtTokenUtil;
import com.ldg.main.payload.request.JwtRequest;
import com.ldg.main.payload.response.JwtResponse;

@RestController
public class JwtAuthenticationController {
    @Autowired
    AuthenticationManager authManager;
    @Autowired
    JwtTokenUtil jwtUtil;

    @PostMapping("/api/auth/login")
    public ResponseEntity<?> login(@RequestBody @Valid JwtRequest request) {
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(), request.getPassword()));

            UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
            String accessToken = jwtUtil.generateAccessToken(user);
            JwtResponse response = new JwtResponse(accessToken);

            return ResponseEntity.ok().body(response);

        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
