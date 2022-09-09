package com.ldg.main.payload.response;

import com.ldg.main.Models.Role;
import com.ldg.main.Models.User;

public class UserResponseForAd {
    public long id;
    public String username;
    public String name;

    public UserResponseForAd(User user) {
        this.id = user.getID();
        this.username = user.getUsername();
        this.name = user.getFirstname() + " " + user.getLastname();
    }
}
