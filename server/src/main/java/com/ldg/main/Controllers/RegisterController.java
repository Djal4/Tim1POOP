package com.ldg.main.Controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ldg.main.Models.User;
import com.ldg.main.Models.UserDetailsImpl;
import com.ldg.main.Repository.UserRepository;
import com.ldg.main.Services.UserService;
import com.ldg.main.config.JwtTokenUtil;
import com.ldg.main.exceptions.HttpStatusCodeException;
import com.ldg.main.payload.request.JwtRequest;
import com.ldg.main.payload.request.RegisterRequest;
import com.ldg.main.payload.response.JwtResponse;

@RestController
@RequestMapping("/api/auth")
public class RegisterController {
    
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    
    private User user;

    @Autowired
    private PasswordEncoder encoder;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterRequest request)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(request));
    }
}
