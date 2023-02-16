package com.theakhinabraham.myshift;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ViewApplication extends AppCompatActivity {

    //TODO: VIEW ALL APPLIED ROLES AND STATUS (FOR STUDENTS)
    //1. View all applied jobs of single user
    //2. Show if: Accepted/Rejected/pending

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_application);
    }
}