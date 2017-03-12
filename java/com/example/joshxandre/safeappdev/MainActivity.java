package com.example.joshxandre.safeappdev;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button blogout;
    EditText name , gender, username, mail, number;
    UserLocalStore  userLocalStore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        blogout = (Button)findViewById(R.id.blogout);
        name = (EditText)findViewById(R.id.name);
        gender = (EditText)findViewById(R.id.gender);
        mail = (EditText)findViewById(R.id.mail);
        number = (EditText)findViewById(R.id.number);
        username = (EditText)findViewById(R.id.username);
        userLocalStore = new UserLocalStore(this);

        blogout.setOnClickListener(this);


    }


    @Override
    protected void onStart() {
        super.onStart();

        if (Authenticate()==true)
        {
            display();
        }
        else
        {
            startActivity(new Intent(MainActivity.this, Login.class));
        }
    }

    public void display()
    {
        User user = userLocalStore.getLoggedInUser();
        username.setText(user.username);
        name.setText(user.fname + user.lname);
        gender.setText(user.gender);
        mail.setText(user.email);
        number.setText(user.contacts);
    }

    private boolean Authenticate()
    {
        return userLocalStore.getLoggedIn();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.blogout:

              userLocalStore.clearData();
                userLocalStore.setUserLoggedIn(false);

                startActivity(new Intent(this,Login.class));
                break;
        }

    }
}
