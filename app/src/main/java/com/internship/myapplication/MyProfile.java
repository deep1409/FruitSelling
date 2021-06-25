package com.internship.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;
import com.google.android.material.textfield.TextInputLayout;
import com.internship.myapplication.Adapter.AdapterHome;
import com.internship.myapplication.Adapter.AdapterHome1;
import com.internship.myapplication.pojo.Profile_Pojo;
import com.internship.myapplication.pojo.pojoHome;
import com.internship.myapplication.pojo.pojoHome1;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Executable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyProfile extends AppCompatActivity {

    ImageView back_arrow, my_profile_edit, my_profile_done,my_profile_close_edit;
    TextInputLayout profile_name, profile_contact_no, profile_address, profile_city, profile_zip_code;
    EditText edt_profile_name, edt_profile_contact_no, edt_profile_address, edt_profile_city, edt_profile_zip_code;

    SharedPreferences sp;
    String shared_email_id;
    String resultt, header, url;
    ExecutorService executorService;
    LoadingAnim loadingAnim,loadingAnimUpdate;
    List<Profile_Pojo> pp;

    String update_url;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        executorService = Executors.newSingleThreadExecutor();
        header = getString(R.string.header);
        try{
            sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            shared_email_id = sp.getString("email_id", "");
        }
        catch (Exception e){
            Log.d("MyProfile", "Exeption: "+e);
        }
        url = header + "retrieve_my_profile.php?customer_email=" + shared_email_id;
        update_url = header + "update_my_profile.php";
        Log.d("uu", "onCreate: "+url);

        //Toast.makeText(this, ""+shared_email_id, Toast.LENGTH_SHORT).show();

        back_arrow = findViewById(R.id.my_profile_back_arrow);
        my_profile_edit = findViewById(R.id.my_profile_edit);
        my_profile_done = findViewById(R.id.my_profile_done);
        profile_name = findViewById(R.id.p_name_layout);
        profile_contact_no = findViewById(R.id.p_contact_layout);
        profile_address = findViewById(R.id.p_address_layout);
        profile_city = findViewById(R.id.p_city_layout);
        profile_zip_code = findViewById(R.id.p_zipcode_layout);
        my_profile_close_edit = findViewById(R.id.my_profile_close_edit);

        edt_profile_name = findViewById(R.id.profile_name);
        edt_profile_contact_no = findViewById(R.id.profile_contact);
        edt_profile_address = findViewById(R.id.profile_address);
        edt_profile_city = findViewById(R.id.profile_city);
        edt_profile_zip_code = findViewById(R.id.profile_zipcode);

        loadingAnim = new LoadingAnim(MyProfile.this);
        loadingAnimUpdate = new LoadingAnim(MyProfile.this);
        getdata();
        //edt_profile_name.setText("Darshan");
        edt_profile_name.setFocusableInTouchMode(false);

        edt_profile_contact_no.setFocusableInTouchMode(false);
        edt_profile_address.setFocusableInTouchMode(false);
        edt_profile_city.setFocusableInTouchMode(false);
        edt_profile_zip_code.setFocusableInTouchMode(false);

        my_profile_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MyProfile.this, "Now you can edit your profile", Toast.LENGTH_SHORT).show();
                my_profile_done.setVisibility(View.VISIBLE);
                my_profile_close_edit.setVisibility(View.VISIBLE);
                my_profile_edit.setVisibility(View.GONE);
                back_arrow.setVisibility(View.GONE);
                edt_profile_name.setFocusableInTouchMode(true);



                edt_profile_contact_no.setFocusableInTouchMode(true);
                edt_profile_address.setFocusableInTouchMode(true);
                edt_profile_city.setFocusableInTouchMode(true);
                edt_profile_zip_code.setFocusableInTouchMode(true);
            }
        });

        my_profile_close_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder ab = new AlertDialog.Builder(view.getContext());
                ab.setCancelable(false);
                ab.setTitle("Warning!!");
                ab.setMessage("Are you sure you do not want to save changes?");
                ab.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        my_profile_close_edit.setVisibility(View.GONE);
                        my_profile_done.setVisibility(View.GONE);
                        my_profile_edit.setVisibility(View.VISIBLE);
                        back_arrow.setVisibility(View.VISIBLE);

                        edt_profile_name.setText(pp.get(0).getCustomer_name());
                        edt_profile_address.setText(pp.get(0).getCustomer_address());
                        edt_profile_city.setText(pp.get(0).getCustomer_city());
                        edt_profile_contact_no.setText(pp.get(0).getCustomer_contact_number());
                        edt_profile_zip_code.setText(pp.get(0).getCustomer_pincode());

                        edt_profile_name.setFocusableInTouchMode(false);

                        edt_profile_contact_no.setFocusableInTouchMode(false);
                        edt_profile_address.setFocusableInTouchMode(false);
                        edt_profile_city.setFocusableInTouchMode(false);
                        edt_profile_zip_code.setFocusableInTouchMode(false);

                        edt_profile_name.clearFocus();
                        edt_profile_contact_no.clearFocus();
                        edt_profile_address.clearFocus();
                        edt_profile_city.clearFocus();
                        edt_profile_zip_code.clearFocus();

                    }
                });
                ab.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog alertDialog = ab.create();
                alertDialog.show();
            }
        });

        my_profile_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingAnimUpdate.startLoadingDialog();
                postData();
//                edt_profile_name.setFocusableInTouchMode(false);
//
//                edt_profile_contact_no.setFocusableInTouchMode(false);
//                edt_profile_address.setFocusableInTouchMode(false);
//                edt_profile_city.setFocusableInTouchMode(false);
//                edt_profile_zip_code.setFocusableInTouchMode(false);
                my_profile_edit.setVisibility(View.VISIBLE);
                my_profile_done.setVisibility(View.GONE);
                my_profile_close_edit.setVisibility(View.GONE);

//                edt_profile_name.setFocusable(false);
//
//                edt_profile_contact_no.setFocusable(false);
//                edt_profile_address.setFocusable(false);
//                edt_profile_city.setFocusable(false);
//                edt_profile_zip_code.setFocusable(false);
            }
        });

        back_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    public void getdata() {

        loadingAnim.startLoadingDialog();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    JsonParser o = new JsonParser();
                    resultt = o.insert(url);
                    pp = new ArrayList<>();
                    JSONObject jsonObject = new JSONObject(resultt);
                    JSONArray jsonArray = jsonObject.getJSONArray("res");
                    Log.v("Login_DATA", "" + resultt);

                    //for fruit
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject11 = jsonArray.getJSONObject(i);
                        Profile_Pojo p = new Profile_Pojo();

                        p.setCustomer_name(jsonObject11.getString("customer_name"));
                        p.setCustomer_contact_number(jsonObject11.getString("customer_contact_number"));
                        p.setCustomer_address(jsonObject11.getString("customer_address"));
                        p.setCustomer_city(jsonObject11.getString("customer_city"));
                        p.setCustomer_pincode(jsonObject11.getString("customer_pincode"));

                        pp.add(p);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    //  Toast.makeText(Login.this, "Please check your Internet Connection and Retry", Toast.LENGTH_LONG).show();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        loadingAnim.dismissDialog();
                        edt_profile_name.setText(pp.get(0).getCustomer_name());
                        edt_profile_address.setText(pp.get(0).getCustomer_address());
                        edt_profile_city.setText(pp.get(0).getCustomer_city());
                        edt_profile_contact_no.setText(pp.get(0).getCustomer_contact_number());
                        edt_profile_zip_code.setText(pp.get(0).getCustomer_pincode());

                    }
                });
            }
        });
    }

    public void postData() {
        AndroidNetworking.post(update_url)
                .addQueryParameter("customer_email", shared_email_id)
                .addQueryParameter("customer_contact_number", edt_profile_contact_no.getText().toString())
                .addQueryParameter("customer_name", edt_profile_name.getText().toString())
                .addQueryParameter("customer_address", edt_profile_address.getText().toString())
                .addQueryParameter("customer_city", edt_profile_city.getText().toString())
                .addQueryParameter("customer_pincode", edt_profile_zip_code.getText().toString())
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
//                        Log.d("Test", "onResponse: "+sign_up_url+"?customer_email="+edt_email.getText().toString()+"&customer_password="+edt_password.getText().toString()+"&customer_contact_number="+edt_contact.getText().toString()+"&customer_name="+edt_name.getText().toString()+"&customer_address="+edt_address.getText().toString()+"&customer_city="+edt_city.getText().toString()+"&customer_pincode="+edt_address_zipcode.getText().toString());
                        loadingAnim.dismissDialog();
                        // do anything with response
                        //if(response.equals("Congratulations, your account has been successfully created.")){
                        Toast.makeText(getApplicationContext(),""+response,Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MyProfile.this,MyProfile.class);
                        startActivity(intent);
                        finish();

//                        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//                        editor = mSharedPreferences.edit();
//                        editor.putString("email_id",edt_email.getText().toString());
//                        editor.putString("password",edt_password.getText().toString());
//                        editor.commit();

                        //}
                    }

                    @Override
                    public void onError(ANError anError) {
                        loadingAnim.dismissDialog();
                        Toast.makeText(MyProfile.this, ""+anError, Toast.LENGTH_SHORT).show();
                    }
                });
    }
}