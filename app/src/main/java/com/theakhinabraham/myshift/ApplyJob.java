package com.theakhinabraham.myshift;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ApplyJob extends AppCompatActivity {

    EditText role, description, salary, locality, requirements;
    Button applyJobBtn;

    //TODO: MAKE RECYCLER VIEW SELECTABLE
    //TODO: ADD DATA TO APPLIED COLLECTION

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_job);
        
        role = findViewById(R.id.role);
        description = findViewById(R.id.description);
        salary = findViewById(R.id.salary);
        locality = findViewById(R.id.locality);
        requirements = findViewById(R.id.requirements);
        applyJobBtn = findViewById(R.id.applyJobBtn);
        
        applyJobBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ApplyJob.this, StudentHome.class);
                startActivity(intent);
                Toast.makeText(ApplyJob.this, "Applied for Job!", Toast.LENGTH_SHORT).show();
            }
        });
        
    }
}