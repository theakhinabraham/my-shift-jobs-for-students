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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class ApplyJob extends AppCompatActivity {

    TextView role, description, salary, locality, requirements, time, jobIdDisplay;
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
        jobIdDisplay = findViewById(R.id.jobIdDisplay);

        messageIntro = findViewById(R.id.messageIntro);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        user = auth.getCurrentUser();
        userId = user.getUid();

        // ---------- MAKE CHANGES BELOW ----------
        CollectionReference studentDB = db.collection("Student");
        DocumentReference reference = studentDB.document(user.getEmail());
        // ---------- MAKE CHANGES ABOVE ----------

        Intent intent = getIntent();
        String job_role_display = intent.getStringExtra("jobRoleDisplay");
        String job_desc_display = intent.getStringExtra("jobDescDisplay");
        String salary_display = intent.getStringExtra("salaryDisplay");
        String locality_display = intent.getStringExtra("localityDisplay");
        String requirements_display = intent.getStringExtra("requirementsDisplay");
        String time_display = intent.getStringExtra("timeDisplay");
        int job_id_display = intent.getIntExtra("jobIdDisplay", 0);

        role.setText(job_role_display);
        description.setText(job_desc_display);
        salary.setText(salary_display);
        locality.setText(locality_display);
        requirements.setText(requirements_display);
        time.setText(time_display);
        jobIdDisplay.setText(String.valueOf(job_id_display));
        applyJobBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ApplyJob.this, StudentHome.class);
                startActivity(intent);
                
                reference.get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.getResult().exists()){

                                    String name_string = task.getResult().getString("fullName");
                                    String email_string = task.getResult().getString("username");
                                    String age_string = task.getResult().getString("age");
                                    String locality_string = task.getResult().getString("locality");
                                    String education_string = task.getResult().getString("education");

                                    String message_string = messageIntro.getText().toString();

                                    CollectionReference appliedDB = db.collection("Applied");

                                    Map<String, Object> applied = new HashMap<>();

                                    //STUDENT DATA
                                    applied.put("fullName", name_string);
                                    applied.put("username", email_string);
                                    applied.put("age", age_string);
                                    applied.put("locality", locality_string);
                                    applied.put("education", education_string);
                                    applied.put("userID", userId);

                                    //APPLICATION STATUS DATA
                                    applied.put("Status", "Pending");

                                    //JOB DATA
                                    applied.put("jobID", job_id_display);
                                    applied.put("role", job_role_display);
                                    applied.put("salary", salary_display);
                                    applied.put("time", time_display);
                                    applied.put("address", locality_display);
                                    String appliedId = userId + "---" + job_id_display;

                                    //MESSAGE DATA
                                    applied.put("message", message_string);
                                    applied.put("appliedID", appliedId);
                                    appliedDB.document(appliedId).set(applied).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(ApplyJob.this, "SUCCESSFULLY APPLIED", Toast.LENGTH_SHORT).show();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(ApplyJob.this, "FAILED TO APPLY: ERROR", Toast.LENGTH_SHORT).show();
                                        }
                                    });


                                }
                            }
                        });

            }
        });
        
    }
}