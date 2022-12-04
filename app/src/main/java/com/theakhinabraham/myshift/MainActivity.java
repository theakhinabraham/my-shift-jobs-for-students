package com.theakhinabraham.myshift;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class MainActivity extends AppCompatActivity {

    EditText email, password;
    Button loginBtn, registerBtn;

    private FirebaseAuth auth;
    private FirebaseFirestore db;
    String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.emailReg);
        password = findViewById(R.id.passwordReg);
        loginBtn = findViewById(R.id.login);
        registerBtn = findViewById(R.id.registerReg);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        CollectionReference studentDB = db.collection("Student");
        CollectionReference companyDB = db.collection("Company");

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        userId = user.getUid();

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newInt = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(newInt);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
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
                                if (task.isSuccessful()) {
                                    //TODO: CHECK IF USER IS STUDENT OR COMPANY
                                    studentDB.whereEqualTo(userId, userId)
                                            .get()
                                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                @Override
                                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                    Toast.makeText(MainActivity.this, "STUDENT - MATT", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                    companyDB.whereEqualTo(userId, userId)
                                            .get()
                                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                @Override
                                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                    Toast.makeText(MainActivity.this, "COMPANY - HARRY", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                    //TODO: REDIRECT TO RESPECTIVE PAGE

                                }
                            }
                        });
            }
        });
    }
}