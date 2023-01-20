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
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class StudentProfile extends AppCompatActivity {

    EditText st_edName, st_edUsername, st_edPassword, st_edAge, st_edLocality, st_edEducation;
    Button st_saveBtn;

    String userId;

    private FirebaseAuth auth;
    private FirebaseFirestore db;
    private FirebaseUser user;

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

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        user = auth.getCurrentUser();
        userId = user.getUid();

        CollectionReference studentDB = db.collection("Student");

        DocumentReference reference = studentDB.document(user.getEmail());

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

                            st_edName.setText(name_string);
                            st_edUsername.setText(email_string);
                            st_edPassword.setText(password_string);
                            st_edAge.setText(age_string);
                            st_edLocality.setText(locality_string);
                            st_edEducation.setText(education_string);

                        }
                    }
                });

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
                    studentDB.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()){
                                for (QueryDocumentSnapshot document : task.getResult()){

                                    Map<String, Object> student = new HashMap<>();
                                    student.put("fullName", std_full_name);
                                    student.put("username", std_email);
                                    student.put("password", std_password);
                                    student.put("locality", std_locality);
                                    student.put("age", std_age);
                                    student.put("education", std_education);
                                    student.put("isStudent", false);
                                    student.put("userID", userId);

                                    if (std_password.length() >= 6) {
                                        db.collection("Student").document(std_email)
                                                .set(student)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void Void) {
                                                        Toast.makeText(StudentProfile.this, "SAVED SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                                                        user.updatePassword(std_password).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                if (task.isSuccessful()) {
                                                                    Toast.makeText(StudentProfile.this, "Password Updated", Toast.LENGTH_SHORT).show();
                                                                }
                                                            }
                                                        });
                                                        user.updateEmail(std_email).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                if (task.isSuccessful()) {
                                                                    Toast.makeText(StudentProfile.this, "Email Updated", Toast.LENGTH_SHORT).show();
                                                                }
                                                            }
                                                        });
                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(StudentProfile.this, "COULD NOT SAVE DATA", Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                    }
                                    else {
                                        Toast.makeText(StudentProfile.this, "Password must be more than 6 characters", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            } else {
                                Toast.makeText(StudentProfile.this, "CANNOT ACCESS DATABASE", Toast.LENGTH_SHORT).show();
                            }
                            Toast.makeText(StudentProfile.this, "Data Saved!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(StudentProfile.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                }
            }
        });
        
    }
}