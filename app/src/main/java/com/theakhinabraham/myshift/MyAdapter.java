package com.theakhinabraham.myshift;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
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
        holder.localityDisplay.setText(job.address);
        holder.requirementsDisplay.setText(job.requirements);
        holder.timeDisplay.setText(job.time);
        holder.job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ApplyJob.class);
                String job_role_display = job.role;
                intent.putExtra("jobRoleDisplay", job_role_display);
                String job_desc_display = job.description;
                intent.putExtra("jobDescDisplay", job_desc_display);
                String salary_display = job.salary;
                intent.putExtra("salaryDisplay", salary_display);
                String locality_display = job.address;
                intent.putExtra("localityDisplay", locality_display);
                String requirements_display = job.requirements;
                intent.putExtra("requirementsDisplay", requirements_display);
                String time_display = job.time;
                intent.putExtra("timeDisplay", time_display);

                view.getContext().startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.job,
                parent, false);
        return new MyViewHolder(v);
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView jobRoleDisplay, jobDescDisplay, salaryDisplay, localityDisplay, requirementsDisplay, timeDisplay;
        CardView job;

        public MyViewHolder (View itemView){
            super(itemView);

            jobRoleDisplay = itemView.findViewById(R.id.jobRoleDisplay);
            jobDescDisplay = itemView.findViewById(R.id.jobDescDisplay);
            salaryDisplay = itemView.findViewById(R.id.salaryDisplay);
            localityDisplay = itemView.findViewById(R.id.localityDisplay);
            requirementsDisplay = itemView.findViewById(R.id.requirementsDisplay);
            timeDisplay = itemView.findViewById(R.id.time);

            job = itemView.findViewById(R.id.jobCard);



        }
    }
}
