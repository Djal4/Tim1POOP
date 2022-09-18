package com.ldg.main.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ldg.main.Models.User;
import com.ldg.main.Repository.UserRepository;
import com.ldg.main.payload.request.RegisterRequest;

import ch.qos.logback.core.encoder.Encoder;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private User user;

    @Autowired
    private PasswordEncoder encoder;

    public UserService()
    {
        user=new User();
    }

    public User saveUser(RegisterRequest request) {
        user.setFirstname(request.getFirstName());
        user.setLastname(request.getLastName());
        user.setRoleID(1);
        user.setPassword(encoder.encode(request.getPassword()));
        user.setUsername(request.getUsername());
        
        return userRepository.save(user);
    }

    public User changePassword(User user, String oldPassword, String newPassword, String newPasswordConfirm) {
        if (encoder.matches(oldPassword, user.getPassword())) {
            if (newPassword.equals(newPasswordConfirm)) {
                user.setPassword(encoder.encode(newPassword));
                return user;// save
            }
        }
        return null;
    }
}
