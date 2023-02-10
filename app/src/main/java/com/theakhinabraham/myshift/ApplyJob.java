package com.theakhinabraham.myshift;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ApplyJob extends AppCompatActivity {

    TextView role, description, salary, locality, requirements, time;
    EditText messageIntro;
    Button applyJobBtn;

    private FirebaseAuth auth;
    private FirebaseFirestore db;
    private FirebaseUser user;

    String userId;

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

        messageIntro = findViewById(R.id.messageIntro);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        user = auth.getCurrentUser();
        userId = user.getUid();

        // ---------- MAKE CHANGES BELOW ----------
        CollectionReference studentDB = db.collection("Student");
        CollectionReference appliedDB = db.collection("Applied");
        DocumentReference reference = studentDB.document(user.getEmail());
        // ---------- MAKE CHANGES ABOVE ----------

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

        applyJobBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ApplyJob.this, StudentHome.class);
                startActivity(intent);
                Toast.makeText(ApplyJob.this, "Applied for Job!", Toast.LENGTH_SHORT).show();

                String message;
                message = messageIntro.getText().toString();

                reference.get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.getResult().exists()){

                                    String name_string = task.getResult().getString("fullName");
                                    String email_string = task.getResult().getString("username");
                                    String password_string = task.getResult().getString("password");
                                    String age_string = task.getResult().getString("age");
                                    String locality_string = task.getResult().getString("locality");
                                    String education_string = task.getResult().getString("education");

                                    //COLLECTED STUDENT DATA
                                    //TODO: STORE STUDENT DATA IN APPLIED DB

                                    //OTHER REMAINING TASKS
                                    //TODO: PUSH JOB DATA TO APPLIED DB
                                    //TODO: ADD APPLICATION STATUS (APPROVED/ REJECTED/ PENDING)

                                }
                            }
                        });

            }
        });
        
    }
}