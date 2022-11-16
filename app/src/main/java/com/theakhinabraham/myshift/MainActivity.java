package com.theakhinabraham.myshift;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText email, password;
    Button loginBtn, registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.emailReg);
        password = findViewById(R.id.passwordReg);
        loginBtn = findViewById(R.id.login);
        registerBtn = findViewById(R.id.registerReg);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newInt = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(newInt);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String emailText = email.getText().toString();
                String passwordText = password.getText().toString();

                if (emailText.matches("storeowner") && passwordText.matches("abc")){
                    Intent intent = new Intent(MainActivity.this, StoreOwnerHome.class);
                    startActivity(intent);
                }

                else if(emailText.matches("student") && passwordText.matches("abc")){
                    Intent i = new Intent(MainActivity.this, StudentHome.class);
                    startActivity(i);
                }

                else {
                    email.setError("Wrong email or password");
                    password.setError("Wrong email or password");
                }

            }
        });

    }
}