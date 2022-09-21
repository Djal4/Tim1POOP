package com.ldg.main.payload.request;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class RegisterRequest implements Serializable{
    
    @NotEmpty
    //@Min(value=4)
    private String firstName;
    @NotEmpty
    private String lastName;

    @NotEmpty
    //@Min(value=4)
    private String username;

    @NotEmpty
    //@Min(value=8)
    @Pattern(regexp="^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",message = "Must contain at least one Uppercase character, one special character and be at least 8 character length.")
    private String password;

    public RegisterRequest()
    {

    }

    public RegisterRequest(String firstName,String lastName,String password,String username)
    {
        this.firstName=firstName;
        this.lastName=lastName;
        this.password=password;
        this.username=username;
    }

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    
}
