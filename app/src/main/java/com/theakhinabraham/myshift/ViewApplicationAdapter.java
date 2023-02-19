package com.theakhinabraham.myshift;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class ViewApplicationAdapter extends FirestoreRecyclerAdapter<Applied, ViewApplicationAdapter.MyViewHolder> {

    public ViewApplicationAdapter(@NonNull FirestoreRecyclerOptions<Applied> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull Applied applied) {
        holder.showJob.setText(applied.role);
        holder.showSalary.setText(applied.salary);
        holder.showLocality.setText(applied.address);
        holder.showStatus.setText(applied.status);
        holder.showHours.setText(applied.time);
        holder.showJobId.setText(String.valueOf(applied.jobID));
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.myapplication,
                parent, false);
        return new MyViewHolder(v);
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView showJob, showHours, showLocality, showStatus, showSalary, showJobId;


        public MyViewHolder (View itemView){
            super(itemView);

            showJob = itemView.findViewById(R.id.showJob);
            showHours = itemView.findViewById(R.id.showHours);
            showLocality = itemView.findViewById(R.id.showLocality);
            showStatus = itemView.findViewById(R.id.showStatus);
            showSalary = itemView.findViewById(R.id.showSalary);
            showJobId = itemView.findViewById(R.id.showJobId);

        }
    }
}
