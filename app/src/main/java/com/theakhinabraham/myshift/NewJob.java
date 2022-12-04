package com.theakhinabraham.myshift;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class NewJob extends AppCompatActivity {

    private FirebaseFirestore db;
    EditText role, description, salary, locality, requirements;
    Button postJobBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_job);

        role = findViewById(R.id.role);
        description = findViewById(R.id.description);
        salary = findViewById(R.id.salary);
        locality = findViewById(R.id.locality);
        requirements = findViewById(R.id.requirements);
        postJobBtn = findViewById(R.id.postJobBtn);

        //Initializing Firebase
        db = FirebaseFirestore.getInstance();

        postJobBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nj_role = role.getText().toString();
                String nj_description = description.getText().toString();
                String nj_salary = salary.getText().toString();
                String nj_locality = locality.getText().toString();
                String nj_requirements = requirements.getText().toString();

                if(nj_role.isEmpty() || nj_description.isEmpty() || nj_salary.isEmpty() || nj_locality.isEmpty() || nj_requirements.isEmpty()){
                    Toast.makeText(NewJob.this, "Please enter all fields!", Toast.LENGTH_SHORT).show();
                }

                else{
                    //Creating Data for FIRESTORE
                    Map<String, Object> job = new HashMap<>();
                    job.put("role", nj_role);
                    job.put("description", nj_description);
                    job.put("salary", nj_salary);
                    job.put("locality", nj_locality);
                    job.put("requirements", nj_requirements);

                    // Add a new document with a generated ID
                    db.collection("Jobs")
                            .add(job)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Toast.makeText(NewJob.this, "Data saved Successfully!", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(NewJob.this, StoreOwnerHome.class);
                                    startActivity(intent);
                                    finish();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
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