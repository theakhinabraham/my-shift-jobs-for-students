package com.theakhinabraham.myshift;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class MyAdapter extends FirestoreRecyclerAdapter<Job, MyAdapter.MyViewHolder> {

    public MyAdapter(@NonNull FirestoreRecyclerOptions<Job> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull Job job) {
        holder.jobRoleDisplay.setText(job.role);
        holder.jobDescDisplay.setText(job.description);
        holder.salaryDisplay.setText(job.salary);
        holder.localityDisplay.setText(job.locality);
        holder.requirementsDisplay.setText(job.requirements);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.job,
                parent, false);
        return new MyViewHolder(v);
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView jobRoleDisplay, jobDescDisplay, salaryDisplay, localityDisplay, requirementsDisplay;

        public MyViewHolder (View itemView){
            super(itemView);

            jobRoleDisplay = itemView.findViewById(R.id.jobRoleDisplay);
            jobDescDisplay = itemView.findViewById(R.id.jobDescDisplay);
            salaryDisplay = itemView.findViewById(R.id.salaryDisplay);
            localityDisplay = itemView.findViewById(R.id.localityDisplay);
            requirementsDisplay = itemView.findViewById(R.id.requirementsDisplay);

        }
    }
}
