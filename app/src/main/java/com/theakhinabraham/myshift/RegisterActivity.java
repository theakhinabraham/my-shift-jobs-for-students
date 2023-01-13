package com.theakhinabraham.myshift;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    EditText fullName, emailReg, passwordReg, age, locality;
    Button registerReg;
    RadioButton studentRadioBtn, companyRadioBtn;
    String userId;

    private FirebaseAuth auth;
    private FirebaseFirestore db;
    private FirebaseUser user;

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

        user = auth.getCurrentUser();
        userId = user.getUid();

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

                //AUTHENTICATION

                auth.createUserWithEmailAndPassword(email_id, password_id).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "Registration Successful", Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Registration Failed!", Toast.LENGTH_LONG).show();
                        }
                    }
                });

                if (TextUtils.isEmpty(full_name) || TextUtils.isEmpty(email_id) || TextUtils.isEmpty(password_id) || TextUtils.isEmpty(ageText) || TextUtils.isEmpty(localityText)) {
                    Toast.makeText(getApplicationContext(), "Please enter all details...", Toast.LENGTH_LONG).show();
                    return;
                }

                else{
                    if (studentRadioBtn.isChecked()) {
                        CollectionReference Student = db.collection("Student");

                        Map<String, Object> student = new HashMap<>();
                        student.put("fullName", full_name);
                        student.put("username", email_id);
                        student.put("password", password_id);
                        student.put("age", ageText);
                        student.put("locality", localityText);
                        student.put("isStudent", true);
                        student.put("userID", userId);
                        Student.document(email_id);

                        // Add a new document with a generated ID
                        db.collection("Student")
                                .add(student)
                                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                    @Override
                                    public void onSuccess(DocumentReference documentReference) {
                                        Toast.makeText(RegisterActivity.this, "Data saved Successfully!", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(RegisterActivity.this, "Please try again!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                    else if (companyRadioBtn.isChecked()) {
                        CollectionReference Company = db.collection("Company");

                        Map<String, Object> company = new HashMap<>();
                        company.put("fullName", full_name);
                        company.put("username", email_id);
                        company.put("password", password_id);
                        company.put("locality", localityText);
                        company.put("isStudent", true);
                        company.put("userID", userId);
                        Company.document("INITIAL");

                        // Add a new document with a generated ID
                        db.collection("Company")
                                .add(company)
                                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                    @Override
                                    public void onSuccess(DocumentReference documentReference) {
                                        Toast.makeText(RegisterActivity.this, "Data saved Successfully!", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(RegisterActivity.this, "Please try again!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                    else {
                        isStudent = false;
                        isCompany = false;
                        Toast.makeText(RegisterActivity.this, "Please select Student/Company", Toast.LENGTH_SHORT).show();
                    }

                }
            }

            });
        }

    }