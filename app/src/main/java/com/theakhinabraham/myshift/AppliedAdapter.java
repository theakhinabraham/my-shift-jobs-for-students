package com.theakhinabraham.myshift;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

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

        holder.rejectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String status = "Rejected";
                Intent intent = new Intent(view.getContext(), AppliedStudents.class);
                intent.putExtra("Status", status);
            }
        });

        holder.acceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String status = "Accepted";
                Intent intent = new Intent(view.getContext(), AppliedStudents.class);
                intent.putExtra("Status", status);
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

        public MyViewHolder (View itemView){
            super(itemView);

            appliedStudentName = itemView.findViewById(R.id.appliedStudentName);
            appliedStudentEmail = itemView.findViewById(R.id.appliedStudentEmail);
            appliedStudentEducation = itemView.findViewById(R.id.appliedStudentEducation);
            appliedStudentLocality = itemView.findViewById(R.id.appliedStudentLocality);
            appliedStudentAge = itemView.findViewById(R.id.appliedStudentAge);

            rejectBtn = itemView.findViewById(R.id.rejectBtn);
            acceptBtn = itemView.findViewById(R.id.acceptBtn);

        }
    }
}
