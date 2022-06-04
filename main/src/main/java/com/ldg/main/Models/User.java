package com.ldg.main.Models;

public class User {
    private long ID;
    private long Picture_id;
    private String Name;
    private String password;
    private String username;
    private String Lastname;
    private int Type=0;
    private String Path="";

    public User()
    {

    }

    public User(long ID,long Picture_id,String Name,String Lastname,int Type,String Path,String password,String username)
    {
        this.ID=ID;
        this.Picture_id=Picture_id;
        this.Name=Name;
        this.Lastname=Lastname;
        this.Type=Type;
        this.Path=Path;
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

    public void setPictureID(long picture_id)
    {
        this.Picture_id=picture_id;
    }

    public long getPictureID()
    {
        return this.Picture_id;
    }

    public void setName(String Name)
    {
        this.Name=Name;
    }

    public String getName()
    {
        return this.Name;
    }

    public void setType(int Type)
    {
        this.Type=Type;
    }

    public int getType()
    {
        return this.Type;
    }

    public void setPath(String Path)
    {
        this.Path=Path;
    }

    public String getPath()
    {
        return this.Path;
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
