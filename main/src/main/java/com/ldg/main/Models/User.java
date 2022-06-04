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

    @Column(name="first_name")
    private String name;

    @Column(name="password")
    private String password;

    @Column(name="username")
    private String username;

    @Column(name="last_name")
    private String lastname;

    @Column(name="role_id")
    private int type=0;

    @Column(name="image_path")
    private String path="";

    public User()
    {

    }

    public User(long ID,long pictureID,String name,String lastname,int type,String path,String password,String username)
    {
        this.ID=ID;
        this.pictureID=pictureID;
        this.name=name;
        this.lastname=lastname;
        this.type=type;
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

    public void setName(String name)
    {
        this.name=name;
    }

    public String getName()
    {
        return this.name;
    }

    public void setLastname(String lastname)
    {
        this.lastname=lastname;
    }

    public String getLastname()
    {
        return this.lastname;
    }

    public void setType(int type)
    {
        this.type=type;
    }

    public int getType()
    {
        return this.type;
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
