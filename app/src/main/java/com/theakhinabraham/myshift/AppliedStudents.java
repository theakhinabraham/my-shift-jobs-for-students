package com.theakhinabraham.myshift;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class AppliedStudents extends AppCompatActivity {

//TODO: VIEW APPLIED STUDENTS RESPECTIVE TO JOB POSTED (FOR COMPANY)
    //1. Show RecyclerView for all students applied for single job
        //User Job userID
    //2. Two clickable buttons: accept/reject: must make changes in Applied DB
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applied_students);
    }
}