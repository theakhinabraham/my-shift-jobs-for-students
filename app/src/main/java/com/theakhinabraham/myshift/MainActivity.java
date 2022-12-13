package com.theakhinabraham.myshift;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity extends AppCompatActivity {

    EditText email, password;
    Button loginStudentBtn, loginCompanyBtn, registerBtn;
    String userId;

    private FirebaseAuth auth;
    private FirebaseFirestore db;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.emailReg);
        password = findViewById(R.id.passwordReg);
        loginStudentBtn = findViewById(R.id.loginStudentBtn);
        loginCompanyBtn = findViewById(R.id.loginCompanyBtn);
        registerBtn = findViewById(R.id.registerReg);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        user = auth.getCurrentUser();
        userId = user.getUid();

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newInt = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(newInt);
            }
        });

        loginStudentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email_id = email.getText().toString();
                String password_id = password.getText().toString();

                if (TextUtils.isEmpty(email_id)) {
                    Toast.makeText(getApplicationContext(), "Please enter email...", Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(password_id)) {
                    Toast.makeText(getApplicationContext(), "Please enter password!", Toast.LENGTH_LONG).show();
                    return;
                }

                auth.signInWithEmailAndPassword(email_id, password_id)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    //TODO: SIGN IN AS STUDENT
                                    //IF EMAIL & PASSWORD MATCHES
                                    db.collection("Student")
                                            .whereEqualTo("username", email_id)
                                            .get()
                                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                @Override
                                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                    if (task.isSuccessful()) {
                                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                                            Intent intentStudent = new Intent(MainActivity.this, StudentHome.class);
                                                            startActivity(intentStudent);
                                                            finish();
                                                            Toast.makeText(MainActivity.this, "SIGNED IN SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                                                        }
                                                    } else {
                                                        Toast.makeText(MainActivity.this, "FAILED!", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });

                                }
                            }
                        });

            }
        });

        loginCompanyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email_id = email.getText().toString();
                String password_id = password.getText().toString();

                if (TextUtils.isEmpty(email_id)) {
                    Toast.makeText(getApplicationContext(), "Please enter email...", Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(password_id)) {
                    Toast.makeText(getApplicationContext(), "Please enter password!", Toast.LENGTH_LONG).show();
                    return;
                }

                auth.signInWithEmailAndPassword(email_id, password_id)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    //TODO: SIGN IN AS COMPANY
                                    //VALIDATE EMAIL & PASSWORD
                                    db.collection("Company")
                                            .whereEqualTo("username", email_id)
                                            .get()
                                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                @Override
                                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                    if (task.isSuccessful()) {
                                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                                            Intent intentCompany = new Intent(MainActivity.this, CompanyHome.class);
                                                            startActivity(intentCompany);
                                                            finish();
                                                            Toast.makeText(MainActivity.this, "SIGNED IN SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                                                        }
                                                    } else {
                                                        Toast.makeText(MainActivity.this, "FAILED!", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });
                                }
                            }
                        });

            }
        });

    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = auth.getCurrentUser();
        if(currentUser != null){
            currentUser.reload();
        }
    }

}