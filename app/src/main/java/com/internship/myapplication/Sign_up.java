package com.internship.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

public class Sign_up extends AppCompatActivity {

    EditText edt_username,edt_password,confirm_password;
    Button login_button;
    String sign_up_url,header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edt_username = findViewById(R.id.edt_username);
        edt_password = findViewById(R.id.edt_password);
        confirm_password = findViewById(R.id.confirm_password);
        login_button = findViewById(R.id.login_button);
        AndroidNetworking.initialize(getApplicationContext());

        header = getString(R.string.header);
        sign_up_url = header + "sign_up.php";

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edt_password.getText().toString().equals(confirm_password.getText().toString()))
                {
                    Toast.makeText(getApplicationContext(),"Access Granted",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Sign_up.this,Login.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Access Denied",Toast.LENGTH_SHORT).show();
                }
            }
        });

        /*

        login_button.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void afterTextChanged(Editable editable) {
                String edt_password = login_button.getText().toString();
                if (editable.length() > 0 && edt_password.length() > 0) {
                    if(!edt_password .equals(confirm_password )){
                        Toast.makeText(getApplicationContext(),"NAHI MILENGA PASSWORD",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        Intent intent = new Intent(MainActivity.this,login.class);
        startActivity(intent);*/

    }

//    public void postData(){
//        AndroidNetworking.post(sign_up_url)
//                .addBodyParameter("firstname", "Amit")
//                .addBodyParameter("lastname", "Shekhar")
//                .setTag("test")
//                .setPriority(Priority.MEDIUM)
//                .build()
//                .getAsJSONObject(new JSONObjectRequestListener() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        // do anything with response
//                    }
//                    @Override
//                    public void onError(ANError error) {
//                        // handle error
//                    }
//                });
//    }
}