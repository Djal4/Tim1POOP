package com.ldg.main.payload.response;

import com.ldg.main.Models.User;

public class UserShortView {
    // public long id;
    public String username;
    public String name;

    public UserShortView(User user) {
        // this.id = user.getID();
        this.username = user.getUsername();
        this.name = user.getFirstname() + " " + user.getLastname();
    }
}
