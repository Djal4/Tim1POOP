package com.ldg.main.Models;

import javax.persistence.*;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue
    @Column(name="id")
    private long ID;

    @Column(name="image_id")
    private long pictureID;

    @Column(name="firstname")
    private String firstname;

    @Column(name="password")
    private String password;

    @Column(name="username")
    private String username;

    @Column(name="lastname")
    private String lastname;

    @Column(name="role_id")
    private int role_id=0;

    @Column(name="image_path")
    private String path="";

    public User()
    {

    }

    public User(long ID,long pictureID,String firstname,String lastname,int role_id,String path,String password,String username)
    {
        this.ID=ID;
        this.pictureID=pictureID;
        this.firstname=firstname;
        this.lastname=lastname;
        this.role_id=role_id;
        this.path=path;
        this.password=password;
        this.username=username;
    }

    public void setID(long id)
    {
        this.ID=id;
    }
    
    public long getID()
    {
        return this.ID;
    }

    public void setPictureID(long pictureID)
    {
        this.pictureID=pictureID;
    }

    public long getPictureID()
    {
        return this.pictureID;
    }

    public void setFirstname(String firstname)
    {
        this.firstname=firstname;
    }

    public String getFirstname()
    {
        return this.firstname;
    }

    public void setLastname(String lastname)
    {
        this.lastname=lastname;
    }

    public String getLastname()
    {
        return this.lastname;
    }

    public void setRoleID(int role_id)
    {
        this.role_id=role_id;
    }

    public int getRoleID()
    {
        return this.role_id;
    }

    public void setPath(String path)
    {
        this.path=path;
    }

    public String getPath()
    {
        return this.path;
    }

    public String getPassword()
    {
        return this.password;
    }

    public void setPassword(String password)
    {
        this.password=password;
    }

    public String getUsername()
    {
        return this.username;
    }

    public void setUsername(String username)
    {
        this.username=username;
    }
}
