package com.theakhinabraham.myshift;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ApplyJob extends AppCompatActivity {

    TextView role, description, salary, locality, requirements, time;
    Button applyJobBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_job);
        
        role = findViewById(R.id.role);
        description = findViewById(R.id.description);
        salary = findViewById(R.id.salary);
        locality = findViewById(R.id.locality);
        requirements = findViewById(R.id.requirements);
        time = findViewById(R.id.timeApply);
        applyJobBtn = findViewById(R.id.applyJobBtn);

        Intent intent = getIntent();
        String job_role_display = intent.getStringExtra("jobRoleDisplay");
        String job_desc_display = intent.getStringExtra("jobDescDisplay");
        String salary_display = intent.getStringExtra("salaryDisplay");
        String locality_display = intent.getStringExtra("localityDisplay");
        String requirements_display = intent.getStringExtra("requirementsDisplay");
        String time_display = intent.getStringExtra("timeDisplay");

        role.setText(job_role_display);
        description.setText(job_desc_display);
        salary.setText(salary_display);
        locality.setText(locality_display);
        requirements.setText(requirements_display);
        time.setText(time_display);

        //TODO: ON BACK BUTTON, APP CRASHES

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