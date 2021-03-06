package com.ldg.main.Services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public boolean checkPassword(String rawPassword,String encodedPassword)
    {
        if(encoder.matches(rawPassword, encodedPassword))
            return true;
        return false;
    }
}
