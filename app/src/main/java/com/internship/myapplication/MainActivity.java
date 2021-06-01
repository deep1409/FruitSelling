package com.internship.myapplication;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Animation animation;
    ConstraintLayout layout;
    SharedPreferences mSP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = findViewById(R.id.constrained_layout);
        animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.fade);
        mSP = getSharedPreferences("login",MODE_PRIVATE);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Boolean sp = mSP.getBoolean("logged", Boolean.parseBoolean(""));

                if (sp == false)
                {
                    startActivity(new Intent(MainActivity.this,Sign_up.class));
                }
                else{
                    startActivity(new Intent(MainActivity.this, Login.class));
                }

                //Intent i = new Intent(Splash.this, Login.class);
                finish();
                //startActivity(i);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        layout.startAnimation(animation);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
