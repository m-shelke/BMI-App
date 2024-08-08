package com.example.bmiapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
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


    //Declaring variable here
    int intWeight = 30;
    int intAge = 25;
    int intcurrentProgress;

    String StringProgress = "0";
    String StringTypeOfUsers = "0";
    String StringWeight = "55";
    String StringAge = "22";

    //onCreate method start from here
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        //Linking XML Content to Java Code
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

     // Objects.requireNonNull(getSupportActionBar()).hide();      Gives nullPointException, need to find alternate solution

        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"white\"></font>"));
        Objects.requireNonNull(getSupportActionBar()).setTitle("Calculate Here..");


        //Finding XML View Id here
        Button calculateBMIBtn =findViewById(R.id.calculateBMIBtn);

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


        //setOnClickListener to male view Clicked
        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Setting background, when view Clicked
                male.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.malefemale_focus));
                //setting Animation to TextView
                txtMale.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.slide_in_left));
                //Setting background, when view Clicked
                female.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.heightcard_bg));
                //Storing "Male" to StringTypesOfUsers
                StringTypeOfUsers = "Male";
            }
        });

        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Setting background, when view Clicked
                female.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.malefemale_focus));
                //setting Animation to TextView
                txtFemale.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.slide_in_left));
                //Setting background, when view Clicked
                male.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.heightcard_bg));
                //Storing "Male" to StringTypesOfUsers
                StringTypeOfUsers = "Female";
            }
        });


        //Setting Max limit of SeekBar 300
        seekBarForHeight.setMax(300);
        //Attaching SeekBar to seekBarForHeight View
        seekBarForHeight.setProgress(0);

        //setOnClickListener to seekBarForHeight, to trigger Action when Clicked
        seekBarForHeight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //Stored progress of SeeKBar Progress to intcurrentProgress Variable which is Integer type
                intcurrentProgress = progress;
                //Type-Casting Integer Value into the String intcurrentProgress --> StringProgress
                StringProgress = String.valueOf(intcurrentProgress);
                //Setting StringProgress to txtCurrentHeight TextView
                txtCurrentHeight.setText(StringProgress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        //setOnClickListener to imgIncreamentAge ImageView
        imgIncreamentAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Increment intAge by 1, if ImageView is Clicked
                intAge = intAge+1;
                //Type-Casting Integer Value into the String intAge --> StringAge
                StringAge = String.valueOf(intAge);
                //Setting intAge to txtCurrentAge TextView
                txtCurrentAge.setText(StringAge);
                //setting Animation to TextView
                txtCurrentAge.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in));
            }
        });

        //setOnClickListener to imgDecrementAge ImageView
        imgDecreamentAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Decrement intAge by 1, if ImageView is Clicked
                intAge = intAge-1;
                //Type-Casting Integer Value into the String intAge --> StringAge
                StringAge = String.valueOf(intAge);
                //Setting intAge to txtCurrentAge TextView
                txtCurrentAge.setText(StringAge);
                //setting Animation to TextView
                txtCurrentAge.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in));
            }
        });

        //setOnClickListener to imgIncreamentWeight ImageView
        imgIncreamentWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Increment intWeight by 1, if ImageView is Clicked
                intWeight = intWeight+1;
                //Type-Casting Integer Value into the String intWeight --> StringWeight
                StringWeight = String.valueOf(intWeight);
                //Setting StringWeight to txtCurrentWeight TextView
                txtCurrentWeight.setText(StringWeight);
                //setting Animation to TextView
                txtCurrentWeight.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in));
            }
        });

        //setOnClickListener to imgDecreamentWeight ImageView
        imgDecreamentWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Decrement intWeight by 1, if ImageView is Clicked
                intWeight = intWeight-1;
                //Type-Casting Integer Value into the String intWeight --> StringWeight
                StringWeight = String.valueOf(intWeight);
                //Setting StringWeight to txtCurrentWeight TextView
                txtCurrentWeight.setText(StringWeight);
                //setting Animation to TextView
                txtCurrentWeight.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in));
            }
        });


        //setOnClickListener to calculateBMIBtn Button
        calculateBMIBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //if (StringTypeOfUsers.equals("0")) means it not selected then show this Toast message
                if (StringTypeOfUsers.equals("0")){
                    Toast.makeText(getApplicationContext(),"Select Your Gender First..!!",Toast.LENGTH_SHORT).show();
                } else if (StringProgress.equals("0")) {
                    //if (StringProgress.equals("0")) means it not selected then show this Toast message
                    Toast.makeText(MainActivity.this, "Select Your Height First..!!", Toast.LENGTH_SHORT).show();
                } else if (intWeight == 0 || intWeight < 0) {
                    //if(intWeight == 0 || intWeight < 0) means it not selected then show this Toast message
                    Toast.makeText(MainActivity.this, "Selected Weight Is Incorrect..!!", Toast.LENGTH_SHORT).show();
                } else if (intAge == 0 || intAge <0) {
                    //if(intAge == 0 || intAge <0) means it not selected then show this Toast message
                    Toast.makeText(MainActivity.this, "Selected Age Is Incorrect..!!", Toast.LENGTH_SHORT).show();
                }else {
                    //else called ShowBMI Activity
                    Intent intent = new Intent(MainActivity.this,ShowBMI.class);

                    //Passing MainActivity Data to ShowBMI Activity  "key and value format"
                    intent.putExtra("gender",StringTypeOfUsers);
                    intent.putExtra("height",StringProgress);
                    intent.putExtra("weight",StringWeight);
                    intent.putExtra("age",StringAge);

                    //calling startActivity method here
                    startActivity(intent);
                    //lastly calling finished method to destroy MainActivity
                    finish();
                }



            }
        });

    }
}