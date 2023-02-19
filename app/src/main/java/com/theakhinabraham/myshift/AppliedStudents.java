package com.theakhinabraham.myshift;

import android.os.Bundle;
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

public class AppliedStudents extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth auth;
    FirebaseUser user;

    private CollectionReference appliedRef = db.collection("Applied");
    private AppliedAdapter appliedAdapter;

    String userId;

    private void setUpRecyclerView() {
        String uid = userId;
        Toast.makeText(this, uid, Toast.LENGTH_SHORT).show();
        Query query = db.collection("Applied").whereEqualTo("userID", userId).orderBy("userID");

        FirestoreRecyclerOptions<Applied> options = new FirestoreRecyclerOptions.Builder<Applied>()
                .setQuery(query, Applied.class)
                .build();

        appliedAdapter = new AppliedAdapter(options);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
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
    }
}