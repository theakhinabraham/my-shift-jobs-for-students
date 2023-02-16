package com.theakhinabraham.myshift;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class CompanyHome extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth auth;
    FirebaseUser user;
    private CollectionReference jobRef = db.collection("Jobs");
    private CompanyAdapter companyAdapter;

    String userId;
    Button applicationBtn, addNewPostBtn, so_profileBtn;

    private void setUpRecyclerView() {
        //TODO: GET VALUES ONLY WHERE "userID" = userId
        String uid = userId;
        Toast.makeText(this, uid, Toast.LENGTH_SHORT).show();
        Query query = db.collection("Jobs").whereEqualTo("userID", userId).orderBy("userID");

        FirestoreRecyclerOptions<Job> options = new FirestoreRecyclerOptions.Builder<Job>()
                .setQuery(query, Job.class)
                .build();

        companyAdapter = new CompanyAdapter(options);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(companyAdapter);
        recyclerView.setItemAnimator(null);
        companyAdapter.notifyDataSetChanged();

    }

    @Override
    protected void onStart(){
        super.onStart();
        companyAdapter.startListening();
    }

    @Override
    protected void onStop(){
        super.onStop();
        companyAdapter.stopListening();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_home);

        applicationBtn = findViewById(R.id.applicationBtn);
        addNewPostBtn = findViewById(R.id.addNewPostBtn);
        so_profileBtn = findViewById(R.id.so_profileBtn);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        userId = user.getUid();

        setUpRecyclerView();
        addNewPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newPostIntent = new Intent(CompanyHome.this, NewJob.class);
                startActivity(newPostIntent);
            }
        });

        applicationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CompanyHome.this, userId, Toast.LENGTH_SHORT).show();
            }
        });

        so_profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToCompanyProfile = new Intent(CompanyHome.this, CompanyProfile.class);
                startActivity(goToCompanyProfile);
                finish();
            }
        });

    }



}