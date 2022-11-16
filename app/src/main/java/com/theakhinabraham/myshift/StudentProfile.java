package com.theakhinabraham.myshift;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StudentProfile extends AppCompatActivity {

    EditText st_edName, st_edUsername, st_edPassword, st_edAge, st_edLocality, st_edEducation;
    Button st_saveBtn;
    
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
        
        st_saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentProfile.this, StudentHome.class);
                startActivity(intent);
                Toast.makeText(StudentProfile.this, "Data Saved!", Toast.LENGTH_SHORT).show();
            }
        });
        
    }
}