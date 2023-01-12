package com.theakhinabraham.myshift;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class CompanyProfile extends AppCompatActivity {

    EditText so_edName, so_edUsername, so_edPassword, so_edLocality;
    Button so_saveBtn;
    String userId;

    private FirebaseAuth auth;
    private FirebaseFirestore db;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_profile);

        so_edName = findViewById(R.id.ed_soName);
        so_edUsername = findViewById(R.id.ed_soUsername);
        so_edPassword = findViewById(R.id.ed_soPassword);
        so_edLocality = findViewById(R.id.ed_soLocality);
        so_saveBtn = findViewById(R.id.so_saveBtn);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        user = auth.getCurrentUser();
        userId = user.getUid();

        CollectionReference companyDB = db.collection("Company");


        //TODO: RETRIEVE AND DISPLAY DATA


        so_saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String str_name = so_edName.getText().toString();
                String str_username = so_edUsername.getText().toString();
                String str_password = so_edPassword.getText().toString();
                String str_locality = so_edLocality.getText().toString();

                if (TextUtils.isEmpty(str_name) || TextUtils.isEmpty(str_username) || TextUtils.isEmpty(str_password) || TextUtils.isEmpty(str_locality)) {
                    Toast.makeText(getApplicationContext(), "Please fill all Details", Toast.LENGTH_LONG).show();
                    return;
                }

                companyDB
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {

                                        Map<String, Object> company = new HashMap<>();
                                        company.put("fullName", str_name);
                                        company.put("username", str_username);
                                        company.put("password", str_password);
                                        company.put("locality", str_locality);
                                        company.put("isStudent", false);
                                        company.put("userID", userId);

                                        if(companyDB.getId() != userId){
                                            companyDB.document(companyDB.getId())
                                                    .delete()
                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void aVoid) {
                                                            Log.d(TAG, "DocumentSnapshot successfully deleted!");
                                                        }
                                                    })
                                                    .addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {
                                                            Log.w(TAG, "Error deleting document", e);
                                                        }
                                                    });
                                        }


                                        db.collection("Company").document(userId)
                                                .set(company)
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void Void) {
                                                        Toast.makeText(CompanyProfile.this, "SAVED SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                                                        user.updateEmail(str_username).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                if (task.isSuccessful()) {
                                                                    Toast.makeText(CompanyProfile.this, "Email Updated", Toast.LENGTH_SHORT).show();
                                                                }
                                                            }
                                                        });
                                                        user.updatePassword(str_password).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {
                                                                    if (task.isSuccessful()) {
                                                                        Toast.makeText(CompanyProfile.this, "Password Updated", Toast.LENGTH_SHORT).show();
                                                                    }
                                                                }
                                                        });
                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(CompanyProfile.this, "COULD NOT SAVE DATA", Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                    }
                                } else {
                                    Toast.makeText(CompanyProfile.this, "CANNOT ACCESS DATABASE", Toast.LENGTH_SHORT).show();
                                }

                                Toast.makeText(CompanyProfile.this, "Data Saved!", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        });

            }
        });
    }}