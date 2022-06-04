package com.ldg.main.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ldg.main.Models.User;
import com.ldg.main.Repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private UserRepository userRepository;
    private Optional<User> user;

    @GetMapping
    public List<User> index()
    {
        List <User> Users=userRepository.findAll();
        return Users;
    }

    @GetMapping("/{user}")
    public User show(@PathVariable(value="user") int ID)
    {
        user=userRepository.findById(ID);
        if(user.isPresent())
            return user.get();
        else
            return null;
    }

    @PutMapping("/{user}")
    public User update(@PathVariable(value="user") int ID)
    {
        user=userRepository.findById(ID);
        return new User();
    }

    @DeleteMapping("/{user}")
    public boolean destroy(@PathVariable(value="user") int id)
    {
        userRepository.delete(this.show(id));
        return true;
    }
}
