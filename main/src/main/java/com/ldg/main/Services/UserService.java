package com.ldg.main.Services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ldg.main.Models.User;

public class UserService {

    private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(10);
    public User saveUser(User user)
    {
        user.setPassword(encoder.encode(user.getPassword()));

        return user;//save user TODO
    }

    public User changePassword(User user,String oldPassword,String newPassword,String newPasswordConfirm)
    {
        if(encoder.matches(oldPassword, user.getPassword()))
        {
            if(newPassword.equals(newPasswordConfirm))
            {
                user.setPassword(encoder.encode(newPassword));
                return user;//save
            }
        }
        return null;
    }
}
