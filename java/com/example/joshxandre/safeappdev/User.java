package com.example.joshxandre.safeappdev;

/**
 * Created by Joshxandre on 11/03/2017.
 */
public class User {
    String fname, lname, username, email, password, gender;
    int  contacts;

    public User (String fname,String lname, String username, String email, String password,String gender, int contacts)
    {

        this.fname = fname;
        this.lname = lname;
        this.username = username;
        this.email = email;
        this.gender = gender;
        this.contacts= contacts;

    }

    public User (String username, String password)
    {
        this.username = username;
        this.password = password;
        this.fname = "";
        this.lname = "";
        this.email = "";
        this.gender = "";
        this.contacts = -1;

    }
}
