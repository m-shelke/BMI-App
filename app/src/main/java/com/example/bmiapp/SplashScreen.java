package com.example.bmiapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Objects;

// After Android 12 (Api 31+) System create it's own SplashScreen, you need to defined your SplashScreen with this annotation "@SuppressLint("CustomSplashScreen")"
@SuppressLint("CustomSplashScreen")
public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

//        Finding .xml View id's
        ImageView imageView = findViewById(R.id.imgLogo);
//        setting animation on imgLogo ImageView
        imageView.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.slide_in_left));
        TextView textView = findViewById(R.id.logotxt);
//        setting animation on logotxt TextView
        textView.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.slide_in_left));

//        setting Application on Full Screen on device
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
      //  Objects.requireNonNull(getSupportActionBar()).hide();

//        Handler class for post delay of Splash Screen
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

//                After Handler class post delay, time-up then we launched SplashScreen Activity to MainActivity
                startActivity(new Intent(SplashScreen.this,MainActivity.class));
//                and finished this activity here
                finish();
            }
//            post delay time in milli second
        },1200);

    }
}


