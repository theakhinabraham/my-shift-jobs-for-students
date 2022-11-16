package com.theakhinabraham.myshift;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StoreOwnerProfile extends AppCompatActivity {

    EditText so_edName, so_edUsername, so_edPassword, so_edLocality;
    Button so_saveBtn;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_owner_profile);

        so_edName = findViewById(R.id.ed_soName);
        so_edUsername = findViewById(R.id.ed_soUsername);
        so_edPassword = findViewById(R.id.ed_soPassword);
        so_edLocality = findViewById(R.id.ed_soLocality);
        so_saveBtn = findViewById(R.id.so_saveBtn);
    
        so_saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(StoreOwnerProfile.this, "Data Saved!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        
    }
}