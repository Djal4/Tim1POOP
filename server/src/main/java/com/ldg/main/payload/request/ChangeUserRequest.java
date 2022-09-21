package com.ldg.main.payload.request;

import javax.validation.constraints.NotEmpty;

public class ChangeUserRequest {
    
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public ChangeUserRequest(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
