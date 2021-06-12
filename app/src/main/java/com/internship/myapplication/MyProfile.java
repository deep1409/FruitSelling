package com.internship.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MyProfile extends AppCompatActivity {

    ImageView my_profile_back_arrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);


        my_profile_back_arrow = findViewById(R.id.my_profile_back_arrow);

        my_profile_back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (MyProfile.this,Home.class);
                startActivity(intent);
            }
        });

    }
}