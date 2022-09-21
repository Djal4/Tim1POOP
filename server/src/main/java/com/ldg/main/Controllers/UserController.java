package com.ldg.main.Controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ldg.main.Services.UserService;
import com.ldg.main.Models.User;
import com.ldg.main.Repository.UserRepository;
import com.ldg.main.Policies.UserPolicy;
import com.ldg.main.Models.UserDetailsImpl; 
import com.ldg.main.payload.request.ChangePasswordRequest;
import com.ldg.main.payload.request.ChangeUserRequest;
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    private Optional<User> user;
    private Authentication auth;

    @Autowired
    private UserPolicy policy;

    @GetMapping
    public List<User> index() {
        auth = SecurityContextHolder.getContext().getAuthentication();
        if(policy.index(auth))
        {
            List<User> Users = userRepository.findAll();
            return Users;
        }
        return null;
    }

    @GetMapping("/{user}")
    public User show(@PathVariable(value = "user") Long ID) {
        if(policy.show())
        {
        user = userRepository.findById(ID);
        if (user.isPresent())
            return user.get();
        }
        return null;
    }

    @PutMapping("/{user}/{role}")
    public boolean changeRole(@PathVariable(value = "user")Long ID,@PathVariable(value="role")long roleID)
    {
        auth = SecurityContextHolder.getContext().getAuthentication();
        if(policy.isAdmin(auth))
        {
            if(userRepository.updateRole(roleID,ID)!=0)
                return true;
        }
        return false;
    }

    @DeleteMapping("/{user}")
    public boolean destroy(@PathVariable(value = "user") Long ID) {
        auth = SecurityContextHolder.getContext().getAuthentication();
        if(policy.delete(auth,ID))
        {
            userRepository.delete(this.show(ID));
            return true;
        }
        return false;
    }
}
