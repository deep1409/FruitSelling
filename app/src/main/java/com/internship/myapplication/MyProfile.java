package com.internship.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class MyProfile extends AppCompatActivity {

    ImageView back_arrow,my_profile_edit,my_profile_done;
    TextInputLayout profile_name, profile_email, profile_contact_no, profile_address, profile_city, profile_zip_code ;
    EditText edt_profile_name, edt_profile_email, edt_profile_contact_no, edt_profile_address, edt_profile_city, edt_profile_zip_code ;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        back_arrow = findViewById(R.id.my_profile_back_arrow);
        my_profile_edit = findViewById(R.id.my_profile_edit);
        my_profile_done = findViewById(R.id.my_profile_done);
        profile_name = findViewById(R.id.p_name_layout);
        profile_email = findViewById(R.id.p_email_layout);
        profile_contact_no = findViewById(R.id.p_contact_layout);
        profile_address = findViewById(R.id.p_address_layout);
        profile_city = findViewById(R.id.p_city_layout);
        profile_zip_code = findViewById(R.id.p_zipcode_layout);

        edt_profile_name = findViewById(R.id.profile_name);
        edt_profile_email = findViewById(R.id.profile_email);
        edt_profile_contact_no = findViewById(R.id.profile_contact);
        edt_profile_address = findViewById(R.id.profile_address);
        edt_profile_city = findViewById(R.id.profile_city);
        edt_profile_zip_code = findViewById(R.id.profile_zipcode);


        edt_profile_name.setText("Darshan");
        edt_profile_name.setFocusableInTouchMode(false);
        edt_profile_email.setFocusableInTouchMode(false);
        edt_profile_contact_no.setFocusableInTouchMode(false);
        edt_profile_address.setFocusableInTouchMode(false);
        edt_profile_city.setFocusableInTouchMode(false);
        edt_profile_zip_code.setFocusableInTouchMode(false);

        my_profile_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MyProfile.this, "Now you can edit your profile", Toast.LENGTH_SHORT).show();
                my_profile_done.setVisibility(View.VISIBLE);
                my_profile_edit.setVisibility(View.GONE);
                edt_profile_name.setFocusableInTouchMode(true);
                edt_profile_email.setFocusableInTouchMode(true);
                edt_profile_contact_no.setFocusableInTouchMode(true);
                edt_profile_address.setFocusableInTouchMode(true);
                edt_profile_city.setFocusableInTouchMode(true);
                edt_profile_zip_code.setFocusableInTouchMode(true);
            }
        });

        my_profile_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edt_profile_name.setFocusableInTouchMode(false);
                edt_profile_email.setFocusableInTouchMode(false);
                edt_profile_contact_no.setFocusableInTouchMode(false);
                edt_profile_address.setFocusableInTouchMode(false);
                edt_profile_city.setFocusableInTouchMode(false);
                edt_profile_zip_code.setFocusableInTouchMode(false);
                my_profile_edit.setVisibility(View.VISIBLE);
                my_profile_done.setVisibility(View.GONE);

                edt_profile_name.setFocusable(false);
                edt_profile_email.setFocusable(false);
                edt_profile_contact_no.setFocusable(false);
                edt_profile_address.setFocusable(false);
                edt_profile_city.setFocusable(false);
                edt_profile_zip_code.setFocusable(false);
            }
        });




        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}