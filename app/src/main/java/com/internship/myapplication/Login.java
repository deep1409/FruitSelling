package com.internship.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.internship.myapplication.pojo.Login_pojo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Executable;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Login extends AppCompatActivity {
    Button login_button;
    TextView signup;
    EditText username,password;
    ArrayList<Login_pojo> model;
    SharedPreferences mSP;
    SharedPreferences mSharedPreferences;
    SharedPreferences.Editor editor;
    String customer_username,customer_password;
    String login_url;
    String result;
    String header;
    LoadingAnim loadingAnim;
    TextInputLayout textInputLayout;
    String cus_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login_button = findViewById(R.id.login_button);

        signup = findViewById(R.id.signup_text);
        username = findViewById(R.id.edt_username_lg);
        password = findViewById(R.id.edt_password_lg);
        textInputLayout = findViewById(R.id.filledTextField_pass);

        mSP = getSharedPreferences("login", Context.MODE_PRIVATE);

        header = getString(R.string.header);
        loadingAnim = new LoadingAnim(Login.this);

        password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (textInputLayout.isErrorEnabled()){
                    textInputLayout.setErrorEnabled(false);
                    textInputLayout.setEndIconActivated(true);
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this,Sign_up.class));
            }
        });

        final ExecutorService executorService = Executors.newSingleThreadExecutor();

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (username.getText().toString().length()==0 ) {
                    username.setError("Username can't be empty");
                }else if(password.getText().toString().isEmpty()){
                    textInputLayout.setError("Password can't be empty");
                }else{
                    login_url = header+"user_login.php?customer_email="+username.getText().toString();
//                    new retrieve().execute();
                    Log.v("Login",""+login_url);
//                    Handler handler = new Handler(Looper.getMainLooper());
                    loadingAnim.startLoadingDialog();
                    executorService.execute(new Runnable() {
                        @Override
                        public void run() {

                            try
                            {
                                JsonParser o = new JsonParser();
                                result = o.insert(login_url);
                                model = new ArrayList<>();

                                JSONObject jsonObject = new JSONObject(result);
                                JSONArray jsonArray = jsonObject.getJSONArray("res");

                                Log.v("Login_DATA",""+result);

                                for (int i = 0; i < jsonArray.length(); i++) {

                                    JSONObject jsonObject11 = jsonArray.getJSONObject(i);
                                    Login_pojo p = new Login_pojo();

                                    p.setCustomer_email(jsonObject11.getString("customer_email"));
                                    p.setCustomer_password(jsonObject11.getString("customer_password"));
                                    model.add(p);

                                    cus_id = jsonObject11.getString("customer_id");

                                    customer_username = p.getCustomer_email();
                                    customer_password = p.getCustomer_password();

                                    Log.v("username","id: "+username.getText().toString() +" pass: "+password.getText().toString());
                                    Log.v("customer_username","id: "+customer_username +" pass: "+customer_password);

                                }
                            }
                            catch ( JSONException e)
                            {
                                e.printStackTrace();
                                //  Toast.makeText(Login.this, "Please check your Internet Connection and Retry", Toast.LENGTH_LONG).show();
                            }

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    //write your UI part here....
                                    if (username.getText().toString().equals(customer_username) && password.getText().toString().equals(customer_password))
                                    {
                                        Toast.makeText(Login.this, " Login sucessful... ", Toast.LENGTH_SHORT).show();
                                        loadingAnim.dismissDialog();
                                        Intent intent = new Intent(Login.this, Home.class);

                                        startActivity(intent);
                                        finish();

                                        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                        editor = mSharedPreferences.edit();
                                        editor.putString("email_id",username.getText().toString());
                                        editor.putString("password",password.getText().toString());
                                        editor.putString("customer_id",cus_id);
                                        editor.commit();

                                        mSP.edit().putBoolean("logged",true).apply();

                                        Log.d("sucess",""+customer_password);
                                        Log.d("sucess",""+customer_username);

                                    }
                                    else{
                                        loadingAnim.dismissDialog();
                                        Toast.makeText(Login.this, " Email or Password is incorrect!! ", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                        }
                    });



                }
            }
        });
    }

//    public class retrieve {
//
//    }


}