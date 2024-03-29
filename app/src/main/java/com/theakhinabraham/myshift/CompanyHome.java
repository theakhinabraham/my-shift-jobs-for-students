package com.theakhinabraham.myshift;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class CompanyHome extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth auth;
    FirebaseUser user;
    private CollectionReference jobRef = db.collection("Jobs");
    private CompanyAdapter companyAdapter;

    String userId;
    Button addNewPostBtn, so_profileBtn;

    private void setUpRecyclerView() {
        String uid = userId;
        Toast.makeText(this, uid, Toast.LENGTH_SHORT).show();
        Query query = db.collection("Jobs").whereEqualTo("userID", userId).orderBy("userID");

        FirestoreRecyclerOptions<Job> options = new FirestoreRecyclerOptions.Builder<Job>()
                .setQuery(query, Job.class)
                .build();

        companyAdapter = new CompanyAdapter(options);

        RecyclerView recyclerView = findViewById(R.id.recyclerView2);
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

        addNewPostBtn = findViewById(R.id.addNewPostBtn);
        so_profileBtn = findViewById(R.id.so_profileBtn);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        userId = user.getUid();

        setUpRecyclerView();

        db.collection("Company").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Map<String, Object> company = new HashMap<>();
                        company.put("userID", userId);
                        db.collection("Company").document(user.getEmail())
                                .update(company).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(CompanyHome.this, "Successful", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                }
            }
        });
        addNewPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newPostIntent = new Intent(CompanyHome.this, NewJob.class);
                startActivity(newPostIntent);
            }
        });

        so_profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToCompanyProfile = new Intent(CompanyHome.this, CompanyProfile.class);
                startActivity(goToCompanyProfile);
            }
        });

    }



}