package com.internship.myapplication;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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

//        if (Build.VERSION.SDK_INT >= 21) {
//            Window window = this.getWindow();
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            window.setStatusBarColor(this.getResources().getColor(R.color.splash_statusbar));
//        }


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
                    startActivity(new Intent(MainActivity.this,Login.class));
                }
                else{
                    startActivity(new Intent(MainActivity.this, Home.class));
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
