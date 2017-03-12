package com.example.joshxandre.safeappdev;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity implements View.OnClickListener{
    Button blogin;
    EditText username;
    EditText password;
    TextView tvregisterlink;

    UserLocalStore  userLocalStore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        blogin = (Button)findViewById(R.id.blogin);
        tvregisterlink = (TextView)findViewById(R.id.tvregisterlink) ;
        userLocalStore = new UserLocalStore(this);

        blogin.setOnClickListener(this);
        tvregisterlink.setOnClickListener(this);
    }




    @Override
    public void onClick(View view) {
        switch(view.getId())
        {
            case R.id.blogin:

                String nusername =  username.getText().toString();
                String npassword = password.getText().toString();

                User user = new User(nusername,npassword);


                authenticate(user);

                break;
            case R.id.tvregisterlink:

                startActivity(new Intent(this,Register.class));

                break;

        }
    }

    private void authenticate (User user)
    {
        ServerRequest serverRequest = new ServerRequest(this);
        serverRequest.fetchUserDataInBackground(user, new GetUserCallBack() {
            @Override
            public void done(User returnedUser) {
                if (returnedUser==null)
                {showErrorMessage();}
                else
                {
                    loggedUserIn(returnedUser);
                }
            }
        });


    }

    private void showErrorMessage()
    {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Login.this);
        dialogBuilder.setMessage("Incorrect Username or Password");
        dialogBuilder.setPositiveButton("Ok", null);
        dialogBuilder.show();
    }

    private void loggedUserIn(User returnedUser)
    {
        userLocalStore.storeUserData(returnedUser);
        userLocalStore.setUserLoggedIn(true);

        startActivity(new Intent(this, MainActivity.class));
    }

}
