package com.example.joshxandre.safeappdev;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Joshxandre on 11/03/2017.
 */
public class UserLocalStore {

    public static final String Sp_Name = "userDetails";
    SharedPreferences userlocalDatabes;

    public UserLocalStore (Context context)
    {
        userlocalDatabes = context.getSharedPreferences(Sp_Name,0);

    }

    public void storeUserData(User user) {
        SharedPreferences.Editor spEditor = userlocalDatabes.edit();

        spEditor.putString("fname", user.fname);
        spEditor.putString("lname", user.lname);
        spEditor.putString("username", user.username);
        spEditor.putString("email", user.email);
        spEditor.putString("password", user.password);
        spEditor.putString("gender", user.gender);
        spEditor.putInt("contacts", user.contacts);
        spEditor.commit();

    }



    public User getLoggedInUser()
    {

        String fname = userlocalDatabes.getString("fname", "");
        String lname = userlocalDatabes.getString("lname", "");
        String username = userlocalDatabes.getString("username", "");
        String email = userlocalDatabes.getString("emaail", "");
        String password = userlocalDatabes.getString("password", "");
        String gender = userlocalDatabes.getString("gender", "");
        int contacts = userlocalDatabes.getInt("contacts", -1);

        User storedUser = new User(username, password, fname, lname, gender, email,  contacts);

        return storedUser;
    }
    public void setUserLoggedIn (Boolean loggedIn)
    {
        SharedPreferences.Editor spEditor = userlocalDatabes.edit();
        spEditor.putBoolean("looggedIn", loggedIn);
        spEditor.commit();

    }

    public boolean getLoggedIn()
    {
        if(userlocalDatabes.getBoolean("loggedIn", false)==true)
        {return true;}
        else
        {return false;}
    }



    public void clearData()
    {
        SharedPreferences.Editor spEditor = userlocalDatabes.edit();
        spEditor.clear();
        spEditor.commit();


    }

}

