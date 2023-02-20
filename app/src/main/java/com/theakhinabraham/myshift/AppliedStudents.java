package com.theakhinabraham.myshift;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class AppliedStudents extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth auth;
    FirebaseUser user;

    private CollectionReference appliedRef = db.collection("Applied");
    private AppliedAdapter appliedAdapter;

    String userId;
    String status;


    private void setUpRecyclerView() {

        Intent i = getIntent();
        int job_id = i.getIntExtra("jobIdDisplay", 250);
        status = i.getStringExtra("status");

        Intent newIntent = new Intent(AppliedStudents.this, AppliedAdapter.class);
        newIntent.putExtra("jobID", job_id);

        Query query = db.collection("Applied").whereEqualTo("jobID", job_id).orderBy("jobID");

        FirestoreRecyclerOptions<Applied> options = new FirestoreRecyclerOptions.Builder<Applied>()
                .setQuery(query, Applied.class)
                .build();

        appliedAdapter = new AppliedAdapter(options);

        RecyclerView recyclerView = findViewById(R.id.appliedRecycler);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(appliedAdapter);
        recyclerView.setItemAnimator(null);
        appliedAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onStart(){
        super.onStart();
        appliedAdapter.startListening();
    }

    @Override
    protected void onStop(){
        super.onStop();
        appliedAdapter.stopListening();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applied_students);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        userId = user.getUid();

        setUpRecyclerView();
    }
}