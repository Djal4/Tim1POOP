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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ldg.main.Models.User;
import com.ldg.main.Models.UserDetailsImpl;
import com.ldg.main.Repository.UserRepository;
import com.ldg.main.Services.UserService;
import com.ldg.main.config.JwtTokenUtil;
import com.ldg.main.exceptions.HttpStatusCodeException;
import com.ldg.main.payload.request.ChangePasswordRequest;
import com.ldg.main.payload.request.JwtRequest;
import com.ldg.main.payload.response.JwtResponse;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authManager;
    @Autowired
    JwtTokenUtil jwtUtil;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private Optional<User> user;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid JwtRequest request) throws HttpStatusCodeException {
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(), request.getPassword()));

            UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
            String accessToken = jwtUtil.generateAccessToken(user);
            JwtResponse response = new JwtResponse(accessToken);

            return ResponseEntity.ok().body(response);

        } catch (BadCredentialsException ex) {
            throw new HttpStatusCodeException(HttpStatus.UNAUTHORIZED, "Bad credentials");
        }
    }

    @PutMapping("/change/password")
    public boolean changePassword(@RequestBody @Valid ChangePasswordRequest request)
    {
        Authentication auth=SecurityContextHolder.getContext().getAuthentication();
        return userService.changePassword(userRepository.findById(((UserDetailsImpl) auth.getPrincipal()).getID()).get(),request);
    }
}
