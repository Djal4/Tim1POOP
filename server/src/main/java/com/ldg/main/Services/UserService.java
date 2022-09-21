package com.ldg.main.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ldg.main.Models.User;
import com.ldg.main.Repository.UserRepository;
import com.ldg.main.payload.request.ChangePasswordRequest;
import com.ldg.main.payload.request.ChangeUserRequest;
import com.ldg.main.payload.request.RegisterRequest;
import java.util.Optional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.ldg.main.Models.UserDetailsImpl;

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

    public boolean changePassword(User usr,ChangePasswordRequest request) 
    {
        if (encoder.matches(request.getOldPassword(), usr.getPassword())) {
            if ((request.getNewPassword()).equals(request.getNewPasswordConfirmed())) {
                if(userRepository.setPassword(encoder.encode(request.getNewPassword()),usr.getID())!=0)
                    return true;
            }
        }

        return false;
    }

    public User updateUser(Long ID,ChangeUserRequest request)
    {

        if(userRepository.updateUser(request.getFirstName(), request.getLastName(), ID)!=0)
            return userRepository.findById(ID).get();
        return null;
    }

}
