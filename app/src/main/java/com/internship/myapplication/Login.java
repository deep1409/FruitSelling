package com.internship.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

    String customer_username,customer_password;
    String login_url;
    String result;
    String header;
    LoadingAnim loadingAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login_button = findViewById(R.id.login_button);

        signup = findViewById(R.id.signup_text);
        username = findViewById(R.id.edt_username_lg);
        password = findViewById(R.id.edt_password_lg);

        header = getString(R.string.header);
        loadingAnim = new LoadingAnim(Login.this);

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
                    password.setError("Password can't be empty");
                }else{
                    login_url = header+"user_login.php?customer_email="+username.getText().toString();
//                    new retrieve().execute();

                    Handler handler = new Handler(Looper.getMainLooper());
                    executorService.execute(new Runnable() {
                        @Override
                        public void run() {
                            loadingAnim.startLoadingDialog();
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

                                    customer_username = p.getCustomer_email();
                                    customer_password = p.getCustomer_password();

                                    Log.v("username","id: "+username +"pass: "+password);

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
                                    if (username.getText().toString().equals(username) && password.getText().toString().equals(password))
                                    {
                                        Toast.makeText(Login.this, " Login sucessful... ", Toast.LENGTH_SHORT).show();
                                        loadingAnim.dismissDialog();
                                        Intent intent = new Intent(Login.this, Home.class);

                                        startActivity(intent);

//                mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//                editor = mSharedPreferences.edit();
//                editor.putString("email_id",edt_username.getText().toString());
//                editor.putString("password",edt_password.getText().toString());
//                editor.commit();
//
//                mSP.edit().putBoolean("logged",true).apply();

                                        Log.d("sucess",""+password);
                                        Log.d("sucess",""+username);

                                    }
                                    else{
                                        loadingAnim.dismissDialog();
                                        Toast.makeText(Login.this, " Email or Password is incorrect!! ", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                        }
                    });

                    Log.v("Login",""+login_url);

                }
            }
        });
    }

//    public class retrieve {
//
//    }


    public class retrieve extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            loadingAnim.startLoadingDialog();
        }

        @Override
        protected Void doInBackground(Void... voids) {

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

                    customer_username = p.getCustomer_email();
                    customer_password = p.getCustomer_password();

                    Log.v("username","id: "+username +"pass: "+password);

                }
            }
            catch ( JSONException e)
            {
                e.printStackTrace();
                //  Toast.makeText(Login.this, "Please check your Internet Connection and Retry", Toast.LENGTH_LONG).show();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if (username.getText().toString().equals(username) && password.getText().toString().equals(password))
            {
                Toast.makeText(Login.this, " Login sucessful... ", Toast.LENGTH_SHORT).show();
                loadingAnim.dismissDialog();
                Intent intent = new Intent(Login.this, Home.class);

                startActivity(intent);

//                mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//                editor = mSharedPreferences.edit();
//                editor.putString("email_id",edt_username.getText().toString());
//                editor.putString("password",edt_password.getText().toString());
//                editor.commit();
//
//                mSP.edit().putBoolean("logged",true).apply();

                Log.d("sucess",""+password);
                Log.d("sucess",""+username);

            }
            else{
                loadingAnim.dismissDialog();
                Toast.makeText(Login.this, " Email or Password is incorrect!! ", Toast.LENGTH_SHORT).show();
            }



        }
    }
}