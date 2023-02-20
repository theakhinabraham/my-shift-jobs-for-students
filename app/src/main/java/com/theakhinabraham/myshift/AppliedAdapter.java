package com.theakhinabraham.myshift;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AppliedAdapter extends FirestoreRecyclerAdapter<Applied, AppliedAdapter.MyViewHolder> {

    public AppliedAdapter(@NonNull FirestoreRecyclerOptions<Applied> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull Applied applied) {
        holder.appliedStudentName.setText(applied.fullName);
        holder.appliedStudentEmail.setText(applied.username);
        holder.appliedStudentEducation.setText(applied.education);
        holder.appliedStudentLocality.setText(applied.locality);
        holder.appliedStudentAge.setText(applied.age);

        String userId;

        FirebaseAuth auth;
        FirebaseFirestore db;
        FirebaseUser user;
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        String studentUID = applied.userID;
        int jobID = applied.jobID;
        String status = applied.status;

        user = auth.getCurrentUser();
        userId = user.getUid();
        CollectionReference appliedRef = db.collection("Applied");

        appliedRef.document(studentUID + "---" + jobID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.getResult().exists()){
                    String status_str = task.getResult().getString("Status");
                    if (status_str.equals("Rejected")){
                        holder.rejectBtn.setBackgroundColor(holder.rejectBtn.getResources().getColor(R.color.gold));
                        holder.acceptBtn.setBackgroundColor(holder.acceptBtn.getResources().getColor(R.color.gray));
                    }
                    else if (status_str.equals("Pending")){
                        holder.rejectBtn.setBackgroundColor(holder.rejectBtn.getResources().getColor(R.color.gray));
                        holder.acceptBtn.setBackgroundColor(holder.acceptBtn.getResources().getColor(R.color.gray));
                    }
                    else if (status_str.equals("Accepted")){
                        holder.rejectBtn.setBackgroundColor(holder.rejectBtn.getResources().getColor(R.color.gray));
                        holder.acceptBtn.setBackgroundColor(holder.acceptBtn.getResources().getColor(R.color.gold));
                    }
                    else {
                        holder.rejectBtn.setBackgroundColor(holder.rejectBtn.getResources().getColor(R.color.gold));
                        holder.acceptBtn.setBackgroundColor(holder.acceptBtn.getResources().getColor(R.color.gold));
                    }
                }
            }
        });

        holder.rejectBtn.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    String status = "Rejected";

                                                    holder.rejectBtn.setBackgroundColor(holder.rejectBtn.getResources().getColor(R.color.gold));
                                                    holder.acceptBtn.setBackgroundColor(holder.acceptBtn.getResources().getColor(R.color.gray));

                                                    Map<String, Object> applied = new HashMap<>();
                                                    applied.put("Status", status);

                                                    appliedRef.document(studentUID + "---" + jobID).update(applied).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void unused) {
                                                            Toast.makeText(view.getContext(), "Status Updated", Toast.LENGTH_SHORT).show();
                                                        }
                                                    }).addOnFailureListener(new OnFailureListener() {
                                                        @Override
                                                        public void onFailure(@NonNull Exception e) {
                                                            Toast.makeText(view.getContext(), "FAILED!!!", Toast.LENGTH_SHORT).show();
                                                        }
                                                    });
                                                }
                                            });

        holder.acceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String status = "Accepted";

                holder.rejectBtn.setBackgroundColor(holder.rejectBtn.getResources().getColor(R.color.gray));
                holder.acceptBtn.setBackgroundColor(holder.acceptBtn.getResources().getColor(R.color.gold));

                Map<String, Object> applied = new HashMap<>();
                applied.put("Status", status);

                appliedRef.document(studentUID + "---" + jobID).update(applied).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(view.getContext(), "Status Updated", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(view.getContext(), "FAILED!!!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.applied,
                parent, false);
        return new MyViewHolder(v);
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView appliedStudentName, appliedStudentEmail, appliedStudentEducation, appliedStudentLocality, appliedStudentAge;
        Button rejectBtn, acceptBtn;

        CardView appliedCard;

        public MyViewHolder (View itemView){
            super(itemView);

            appliedStudentName = itemView.findViewById(R.id.appliedStudentName);
            appliedStudentEmail = itemView.findViewById(R.id.appliedStudentEmail);
            appliedStudentEducation = itemView.findViewById(R.id.appliedStudentEducation);
            appliedStudentLocality = itemView.findViewById(R.id.appliedStudentLocality);
            appliedStudentAge = itemView.findViewById(R.id.appliedStudentAge);

            rejectBtn = itemView.findViewById(R.id.rejectBtn);
            acceptBtn = itemView.findViewById(R.id.acceptBtn);

            appliedCard = itemView.findViewById(R.id.jobCard);


        }
    }
}
