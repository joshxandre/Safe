package com.example.joshxandre.safeappdev;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Register extends AppCompatActivity implements View.OnClickListener {

    Button bregister;
    EditText f_name, l_name,Gender, email, Contact, rusername, rpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        bregister = (Button)findViewById(R.id.bregister);
        f_name = (EditText)findViewById(R.id.fname);
        l_name = (EditText)findViewById(R.id.lname);
        Contact = (EditText)findViewById(R.id.contact);
        Gender = (EditText)findViewById(R.id.gender);
        email = (EditText)findViewById(R.id.email);
        rusername = (EditText)findViewById(R.id.rusername);
        rpassword = (EditText)findViewById(R.id.rpassword);

        bregister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
           case R.id.bregister:

               String fname = f_name.getText().toString();
               String lname = l_name.getText().toString();
               String username = rusername.getText().toString();
               String password = rpassword.getText().toString();
               String remail = email.getText().toString();
               String gender = Gender.getText().toString();
               int contacts = Integer.parseInt(Contact.getText().toString());

                User user = new User(username, password,fname, lname, gender, remail, contacts);

                registerUser(user);
            break;
        }

    }

    private void registerUser(User user)
    {
        ServerRequest serverRequest = new ServerRequest(this);
        serverRequest.storeUserDataInBackground(user, new GetUserCallBack() {
            @Override
            public void done(User returnedUser) {
                startActivity(new Intent(Register.this, Login.class));
            }
        });
    }
}
