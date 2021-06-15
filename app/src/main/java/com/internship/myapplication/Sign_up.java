package com.internship.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.StringRequestListener;

public class Sign_up extends AppCompatActivity {

    EditText edt_name,edt_email,edt_contact,edt_address,edt_city,edt_address_zipcode,edt_password,confirm_password;
    Button login_button;
    String sign_up_url,header,authentication_url;
    LoadingAnim loadingAnim;
    SharedPreferences mSharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edt_name = findViewById(R.id.edt_name);
        edt_email =findViewById(R.id.edt_email);
        edt_contact = findViewById(R.id.edt_contact);
        edt_address = findViewById(R.id.edt_address);
        edt_city = findViewById(R.id.edt_city);
        edt_address_zipcode = findViewById(R.id.edt_address_zipcode);
        edt_password = findViewById(R.id.edt_password);
        confirm_password = findViewById(R.id.confirm_password);
        login_button = findViewById(R.id.login_button);

        AndroidNetworking.initialize(getApplicationContext());

        header = getString(R.string.header);
        sign_up_url = header + "sign_up.php";
        authentication_url = header + "sign_up_authentication.php";

        loadingAnim = new LoadingAnim(Sign_up.this);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (edt_name.getText().toString().length()==0 ) {
                    edt_name.setError("Username can't be empty");
                }else if(edt_email.getText().length()==0){
                    edt_email.setError("Email can't be empty");
                }else if(edt_contact.getText().length()==0){
                    edt_contact.setError("contact can't be empty");
                }else if(edt_address.getText().length()==0){
                    edt_address.setError("Address can't be empty");
                }else if(edt_address_zipcode.getText().length()==0){
                    edt_address_zipcode.setError("ZipCode can't be empty");
                }else if(edt_password.getText().length()==0){
                    edt_password.setError("Password can't be empty");
                }else if(confirm_password.getText().length()==0){
                    confirm_password.setError("Confirm Password can't be empty");
                }else if(confirm_password.getText().length()==0 && edt_password.getText().length()==0){
                    edt_password.setError("password cant be empty");
                }else if(edt_password.getText().toString().equals(confirm_password.getText().toString()))
                {
                    authentication();
                    //Toast.makeText(getApplicationContext(),"Access Granted",Toast.LENGTH_SHORT).show();
                    //Intent intent = new Intent(Sign_up.this,Login.class);
                    //startActivity(intent);
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

    public void authentication(){

        loadingAnim.startLoadingDialog();
        AndroidNetworking.get(authentication_url)
                .addQueryParameter("customer_email" , edt_email.getText().toString())
                .setTag("Test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        // do anything with response
                        if(response.equals("you can create your account") ){
                            postData();
                        }
                        if(response.equals( "email already exists")){
                            loadingAnim.dismissDialog();
                            Toast.makeText(getApplicationContext(),"Email has already been taken",Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        loadingAnim.dismissDialog();
                        Toast.makeText(getApplicationContext(),""+anError,Toast.LENGTH_SHORT).show();
                    }
                });

    }

    //https://prolonged-lake.000webhostapp.com/FruitSeller/sign_up.php?customer_email=ab@gmail.com&customer_password=12345&customer_contact_number=7412589630&customer_name=abcd&customer_address=vadodara&customer_city=vadodara&customer_pincode=390001

    public void postData(){
        AndroidNetworking.post(sign_up_url/*+"?customer_email="+edt_email.getText().toString()+"&customer_password="+edt_password.getText().toString()+"&customer_contact_number="+edt_contact.getText().toString()+"&customer_name="+edt_name.getText().toString()+"&customer_address="+edt_address.getText().toString()+"&customer_city="+edt_city.getText().toString()+"&customer_pincode="+edt_address_zipcode.getText().toString()*/)
                .addQueryParameter("customer_email", edt_email.getText().toString())
                .addQueryParameter("customer_contact_number", edt_contact.getText().toString())
                .addQueryParameter("customer_name", edt_name.getText().toString())
                .addQueryParameter("customer_address", edt_address.getText().toString())
                .addQueryParameter("customer_city", edt_city.getText().toString())
                .addQueryParameter("customer_pincode", edt_address_zipcode.getText().toString())
                .addQueryParameter("customer_password", edt_password.getText().toString())
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Test", "onResponse: "+sign_up_url+"?customer_email="+edt_email.getText().toString()+"&customer_password="+edt_password.getText().toString()+"&customer_contact_number="+edt_contact.getText().toString()+"&customer_name="+edt_name.getText().toString()+"&customer_address="+edt_address.getText().toString()+"&customer_city="+edt_city.getText().toString()+"&customer_pincode="+edt_address_zipcode.getText().toString());
                        loadingAnim.dismissDialog();
                        // do anything with response
                        //if(response.equals("Congratulations, your account has been successfully created.")){
                        Toast.makeText(getApplicationContext(),""+response,Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Sign_up.this,Home.class);
                        intent.putExtra("flag","1");
                        startActivity(intent);

                        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        editor = mSharedPreferences.edit();
                        editor.putString("email_id",edt_email.getText().toString());
                        editor.putString("password",edt_password.getText().toString());
                        editor.commit();

                        //}
                    }

                    @Override
                    public void onError(ANError anError) {
                        loadingAnim.dismissDialog();
                        Toast.makeText(Sign_up.this, ""+anError, Toast.LENGTH_SHORT).show();
                    }
                });
    }
}