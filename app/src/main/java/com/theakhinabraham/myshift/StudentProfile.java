package com.theakhinabraham.myshift;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentProfile extends AppCompatActivity {

    EditText st_edName, st_edUsername, st_edPassword, st_edAge, st_edLocality, st_edEducation;
    Button st_saveBtn;

    private FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);

        st_edName = findViewById(R.id.st_edName);
        st_edUsername = findViewById(R.id.st_edUsername);
        st_edPassword = findViewById(R.id.st_edPassword);
        st_edAge = findViewById(R.id.st_edAge);
        st_edLocality = findViewById(R.id.st_edLocality);
        st_edEducation = findViewById(R.id.st_edEducation);
        st_saveBtn = findViewById(R.id.st_saveBtn);

        db = FirebaseFirestore.getInstance();
        
        st_saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(StudentProfile.this, "Data Saved!", Toast.LENGTH_SHORT).show();

                String std_full_name = st_edName.getText().toString();
                String std_email = st_edUsername.getText().toString();
                String std_password = st_edPassword.getText().toString();
                String std_age = st_edAge.getText().toString();
                String std_locality = st_edLocality.getText().toString();
                String std_education = st_edEducation.getText().toString();

                if (std_full_name.isEmpty() || std_email.isEmpty() || std_password.isEmpty() || std_age.isEmpty() || std_locality.isEmpty() || std_education.isEmpty()){
                    Toast.makeText(StudentProfile.this, "Please enter all fields!", Toast.LENGTH_SHORT).show();
                }

                else{
                    //TODO: UPDATE FIRESTORE & DISPLAY UPDATED DATA
                }

            }
        });
        
    }
}