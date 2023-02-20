package com.theakhinabraham.myshift;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class StudentHome extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth auth;
    FirebaseUser user;
    private CollectionReference jobRef = db.collection("Jobs");
    private MyAdapter myAdapter;
    String userId;
    Button applyPostBtn, st_profileBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);

        setUpRecyclerView();

        applyPostBtn = findViewById(R.id.st_applyJobBtn);
        st_profileBtn = findViewById(R.id.st_editProfile);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        userId = user.getUid();
        st_profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(StudentHome.this, StudentProfile.class);
                startActivity(i);
            }
        });

        applyPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newInt = new Intent(StudentHome.this, ViewApplication.class);
                startActivity(newInt);
            }
        });

    }

    private void setUpRecyclerView() {
        Query query = jobRef.whereEqualTo("isAvailable", true);

        FirestoreRecyclerOptions<Job> options = new FirestoreRecyclerOptions.Builder<Job>()
                .setQuery(query, Job.class)
                .build();


        myAdapter = new MyAdapter(options);

        RecyclerView recyclerView = findViewById(R.id.recyclerView2);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);
        recyclerView.setItemAnimator(null);
        myAdapter.notifyDataSetChanged();

    }

    @Override
    protected void onStart(){
        super.onStart();
        myAdapter.startListening();
    }

    @Override
    protected void onStop(){
        super.onStop();
        myAdapter.stopListening();
    }

}