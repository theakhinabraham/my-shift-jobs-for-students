package com.theakhinabraham.myshift;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<Job> jobArrayList;

    public MyAdapter(Context context, ArrayList<Job> jobArrayList) {
        this.context = context;
        this.jobArrayList = jobArrayList;
    }

    public MyAdapter() {
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.job,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Job job = jobArrayList.get(position);

        holder.role.setText(job.role);
        holder.description.setText(job.description);
        holder.salary.setText(String.valueOf(job.salary));
        holder.locality.setText(job.locality);
        holder.requirements.setText(job.requirements);

    }

    @Override
    public int getItemCount() {
        return jobArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView role, description, salary, locality, requirements;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            role = itemView.findViewById(R.id.jobRoleDisplay);
            description = itemView.findViewById(R.id.jobDescDisplay);
            salary = itemView.findViewById(R.id.salaryDisplay);
            locality = itemView.findViewById(R.id.localityDisplay);
            requirements = itemView.findViewById(R.id.requirementsDisplay);

        }
    }
}
