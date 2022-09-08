package com.ldg.main.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ldg.main.Models.User;
import com.ldg.main.Repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(13);

    public User saveUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));

        // return user;

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
