package com.theakhinabraham.myshift;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class ViewApplication extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth auth;
    FirebaseUser user;

    private ViewApplicationAdapter viewApplicationAdapter;

    String userId;

    private void setUpRecyclerView() {
        String uid = userId;
        Toast.makeText(this, uid, Toast.LENGTH_SHORT).show();
        Query query = db.collection("Applied").whereEqualTo("userID", userId).orderBy("jobID");

        FirestoreRecyclerOptions<Applied> options = new FirestoreRecyclerOptions.Builder<Applied>()
                .setQuery(query, Applied.class)
                .build();

        viewApplicationAdapter = new ViewApplicationAdapter(options);

        RecyclerView recyclerView = findViewById(R.id.viewAppRecycler);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(viewApplicationAdapter);
        recyclerView.setItemAnimator(null);
        viewApplicationAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onStart(){
        super.onStart();
        viewApplicationAdapter.startListening();
    }

    @Override
    protected void onStop(){
        super.onStop();
        viewApplicationAdapter.stopListening();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_application);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        userId = user.getUid();

        setUpRecyclerView();

    }
}