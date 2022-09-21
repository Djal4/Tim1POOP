package com.ldg.main.Policies;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.ldg.main.Controllers.AuthController;
import com.ldg.main.Models.User;
import com.ldg.main.Repository.UserRepository;
import com.ldg.main.Services.UserService;
import com.ldg.main.Models.UserDetailsImpl;

@Service
public class UserPolicy {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    UserService userService;
    private User user;
    private Optional<User> usr;

    public UserPolicy() {
        userService = new UserService();
    }

    public boolean isAdmin(Authentication auth) {
        user = getUser(auth);
        if (user.getRoleID() == 2)
            return true;
        return false;
    }

    public boolean index(Authentication auth) {
        user = getUser(auth);
        if (user.getRoleID() == 2)
            return true;
        return false;
    }

    public boolean show() {
        return true;
    }

    public boolean update(Authentication auth, long ID) {
        user = getUser(auth);
        if (user.getRoleID() == 2 || user.getID() == ID)
            return true;
        return false;
    }

    public boolean delete(Authentication auth, long ID) {
        user = getUser(auth);
        if (user.getRoleID() == 2 || user.getID() == ID)
            return true;
        return false;
    }

    private User getUser(Authentication auth) {
        usr = userRepository.findById(((UserDetailsImpl) auth.getPrincipal()).getID());
        if (usr.isPresent())
            return usr.get();
        return null;
    }
}
