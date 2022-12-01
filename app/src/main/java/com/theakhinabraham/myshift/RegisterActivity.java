package com.theakhinabraham.myshift;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegisterActivity extends AppCompatActivity {

    EditText fullName, emailReg, passwordReg, age, locality;
    Button registerReg;
    RadioButton studentRadioBtn, companyRadioBtn;

    private FirebaseAuth auth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fullName = findViewById(R.id.name);
        emailReg = findViewById(R.id.emailReg);
        passwordReg = findViewById(R.id.passwordReg);
        registerReg = findViewById(R.id.registerReg);
        studentRadioBtn = findViewById(R.id.studentRadioBtn);
        companyRadioBtn = findViewById(R.id.companyRadioBtn);
        age = findViewById(R.id.age);
        locality = findViewById(R.id.locality);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        registerReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String full_name, email_id, password_id, ageText, localityText;
                boolean isStudent = false;
                boolean isCompany = false;

                full_name = fullName.getText().toString();
                email_id = emailReg.getText().toString();
                password_id = passwordReg.getText().toString();
                ageText = age.getText().toString();
                localityText = locality.getText().toString();

                if (studentRadioBtn.isChecked()) {
                    isStudent = true;
                    isCompany = false;

                }
                else if (companyRadioBtn.isChecked()) {
                    isStudent = false;
                    isCompany = true;
                }
                else {
                    isStudent = false;
                    isCompany = false;
                    Toast.makeText(RegisterActivity.this, "Please select Student/Company", Toast.LENGTH_SHORT).show();
                }

                auth.createUserWithEmailAndPassword(email_id, password_id)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Registration successful!", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                    startActivity(intent);
                                }
                                else {
                                    Toast.makeText(getApplicationContext(), "Registration failed! Please try again later", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }

            });
        }

    }