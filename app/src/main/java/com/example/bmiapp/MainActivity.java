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
import android.widget.Toast;

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


    int intWeight = 30;
    int intAge = 25;
    int currentProgress;

    String intProgress = "0";
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
        TextView txtCurrentAge = findViewById(R.id.currentAge);
        TextView txtMale = findViewById(R.id.txtMale);
        TextView txtFemale = findViewById(R.id.txtFemale);

        ImageView imgIncreamentWeight = findViewById(R.id.increamentWeight);
        ImageView imgDecreamentWeight = findViewById(R.id.decreamentWeight);
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
        seekBarForHeight.setProgress(0);

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


        imgIncreamentAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intAge = intAge+1;
                age = String.valueOf(intAge);
                txtCurrentAge.setText(age);
                txtCurrentAge.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in));
            }
        });

        imgDecreamentAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intAge = intAge-1;
                age = String.valueOf(intAge);
                txtCurrentAge.setText(age);
                txtCurrentAge.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in));
            }
        });


        imgIncreamentWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intWeight = intWeight+1;
                weight = String.valueOf(intWeight);
                txtCurrentWeight.setText(weight);
                txtCurrentWeight.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in));
            }
        });

        imgDecreamentWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intWeight = intWeight-1;
                weight = String.valueOf(intWeight);
                txtCurrentWeight.setText(weight);
                txtCurrentWeight.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in));
            }
        });




        //TextView textView = findViewById(R.id.txtMale);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (typeOfUsers.equals("0")){
                    Toast.makeText(getApplicationContext(),"Select Your Gender First..!!",Toast.LENGTH_SHORT).show();
                } else if (intProgress.equals("0")) {
                    Toast.makeText(MainActivity.this, "Select Your Height First..!!", Toast.LENGTH_SHORT).show();
                } else if (intWeight == 0 || intWeight < 0) {
                    Toast.makeText(MainActivity.this, "Selected Weight Is Incorrect..!!", Toast.LENGTH_SHORT).show();
                } else if (intAge == 0 || intAge <0) {
                    Toast.makeText(MainActivity.this, "Selected Age Is Incorrect..!!", Toast.LENGTH_SHORT).show();
                }else {

                    Intent intent = new Intent(MainActivity.this,ShowBMI.class);

                    intent.putExtra("gender",typeOfUsers);
                    intent.putExtra("height",intProgress);
                    intent.putExtra("weight",weight);
                    intent.putExtra("age",age);

                    startActivity(intent);
                    finish();
                }



            }
        });

    }
}