package com.example.bmiapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {


    int intWeight = 55;
    int intAge = 22;
    int currentProgress;

    String intProgress = "150";
    String typeOfUsers = "0";
    String weight = "55";
    String age = "22";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

     // Objects.requireNonNull(getSupportActionBar()).hide();

        Button button =findViewById(R.id.calculateBMIBtn);

        TextView txtCurrentHeight = findViewById(R.id.currentHeight);
        TextView txtCurrentWeight = findViewById(R.id.currentWeight);
        TextView txtMale = findViewById(R.id.txtMale);
        TextView txtFemale = findViewById(R.id.txtFemale);

        ImageView imgIncreamentWeight = findViewById(R.id.increamentWeight);
        ImageView imgDecreamentWeighyt = findViewById(R.id.decreamentWeight);
        ImageView imgIncreamentAge = findViewById(R.id.increamentAge);
        ImageView imgDecreamentAge = findViewById(R.id.decreamentAge);

        SeekBar seekBarForHeight = findViewById(R.id.seekbarForHeight);

        RelativeLayout male = findViewById(R.id.male);
        RelativeLayout female = findViewById(R.id.female);


        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                male.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemale_focus));
                txtMale.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.slide_in_left));
                female.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.heightcard_bg));
                typeOfUsers = "Male";
            }
        });

        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                female.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.malefemale_focus));
                txtFemale.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.slide_in_left));
                male.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.heightcard_bg));
                typeOfUsers = "Female";
            }
        });


        seekBarForHeight.setMax(300);
        seekBarForHeight.setProgress(150);

        seekBarForHeight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                currentProgress = progress;
                intProgress = String.valueOf(currentProgress);  //Type casting Int to String
                txtCurrentHeight.setText(intProgress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });






        //TextView textView = findViewById(R.id.txtMale);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ShowBMI.class);
                startActivity(intent);

                // textView.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.slide_in_left));     it's work..!!
            }
        });

    }
}