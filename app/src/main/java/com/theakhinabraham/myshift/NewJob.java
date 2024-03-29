package com.theakhinabraham.myshift;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class NewJob extends AppCompatActivity {

    EditText role, description, salary, address, requirements, time;
    int numberCount;
    Button postJobBtn;
    String userId;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth auth;
    FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_job);

        role = findViewById(R.id.role);
        description = findViewById(R.id.description);
        salary = findViewById(R.id.salary);
        address = findViewById(R.id.locality);
        requirements = findViewById(R.id.requirements);
        postJobBtn = findViewById(R.id.postJobBtn);
        time = findViewById(R.id.time);

        //Initializing Firebase
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        userId = user.getUid();

        db.collection("Jobs")
                .whereEqualTo("isJob", true)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            numberCount = 0;
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                int currentCount = task.getResult().size();
                                if (currentCount > numberCount){
                                    numberCount = currentCount;
                                }
                                else {
                                    continue;
                                }
                            }
                            Toast.makeText(NewJob.this, String.valueOf(numberCount), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        postJobBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nj_role = role.getText().toString();
                String nj_description = description.getText().toString();
                String nj_salary = salary.getText().toString();
                String nj_address = address.getText().toString();
                String nj_requirements = requirements.getText().toString();
                String nj_time = time.getText().toString();



                if(nj_role.isEmpty() || nj_description.isEmpty() || nj_salary.isEmpty() || nj_address.isEmpty() || nj_requirements.isEmpty()){
                    Toast.makeText(NewJob.this, "Please enter all fields!", Toast.LENGTH_SHORT).show();
                }

                else{
                    int count = 0;

                    //Creating Data for FIRESTORE
                    Map<String, Object> job = new HashMap<>();
                    job.put("role", nj_role);
                    job.put("description", nj_description);
                    job.put("salary", nj_salary);
                    job.put("address", nj_address);
                    job.put("requirements", nj_requirements);
                    job.put("isAvailable", true);
                    job.put("jobID", numberCount);
                    job.put("userID", userId);
                    job.put("isJob", true);
                    job.put("time", nj_time);
                    String jobMainID = userId + "---" + numberCount;
                    job.put("jobMainID", jobMainID);

                    db.collection("Jobs").document(jobMainID).set(job).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(NewJob.this, "Data saved Successfully!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(NewJob.this, CompanyHome.class);
                            startActivity(intent);
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(NewJob.this, "Please try again!", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });

    }
}