package com.example.bmiapp;

// all the package is imported here
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Objects;

public class ShowBMI extends AppCompatActivity {

//    Declaring variable
    String bmi;
    String height;
    String weight;

    float intbmi;
    float intHeight,intWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_show_bmi);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


//        Finding id's of the .xml View
        TextView txtbmiDisplay = findViewById(R.id.bmiDisplay);
        TextView txtgenderDisplay = findViewById(R.id.genderDisplytxt);
        TextView bmiCategory = findViewById(R.id.bmiCategory);

        ImageView imgDisplay = findViewById(R.id.resultImg);
//        RelativeLayout background = findViewById(R.id.contentLayout);

        LinearLayout linearLayoutLink1 = findViewById(R.id.link1);
        LinearLayout linearLayoutLink2 = findViewById(R.id.link2);

        Button recalculateBMIBtn = findViewById(R.id.recalculateBMIBtn);


        //        setting ActionBar and also setting font color of the ActionBar Title
        Objects.requireNonNull(getSupportActionBar()).setElevation(0);
          getSupportActionBar().setTitle(Html.fromHtml("<font color=\"white\"></font>"));
        //        setting ActionBar Title
        getSupportActionBar().setTitle("Result is..");

//        getting color form the Color Drawable Resources
        ColorDrawable colorDrawable = new ColorDrawable(getColor(R.color.black));
//        setting background color of the ActionBar as a Black
        getSupportActionBar().setBackgroundDrawable(colorDrawable);

//        setting onClickedListener on the linearLayoutLink1 click or link 1 is get clicked
        linearLayoutLink1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Navigate to the next activity, on linearLayoutLink1 clicked or link 1 is clicked
                startActivity(new Intent(ShowBMI.this,BasedOn1.class));
            }
        });

        //        setting onClickedListener on the linearLayoutLink1 click or link 1 is get clicked
        linearLayoutLink2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //                Navigate to the next activity, on linearLayoutLink1 clicked or link 1 is clicked
                startActivity(new Intent(ShowBMI.this, Based2.class));
            }
        });

//        getting instance of the Intent class for the purpose of getting value form MainActivity
        Intent intent;
//        instance of the Intent Class
        intent = getIntent();

//        getting users Height from MainActivity
       height = intent.getStringExtra("height");
        //        getting users Weight from MainActivity
        weight =  intent.getStringExtra("weight");

//        Type Casting String Height into Float
         intHeight = Float.parseFloat(height);
        //        Type Casting String Weight into Float
        intWeight = Float.parseFloat(weight);

//        Dividing value of Height, that we get from the MainActivity and Users
        intHeight = intHeight/100;

//        Calculating BMI formula
        intbmi = intWeight/(intHeight*intHeight);

//        //        Type Casting Flaot intbmi into String
        bmi = Float.toString(intbmi);

//        if the BMI is less 18.5, then
        if (intbmi<18.5){
//            setting text to TextView as,
            bmiCategory.setText("Underweight");
           // background.setBackgroundColor(Color.RED);
            imgDisplay.setImageResource(R.drawable.underweight);
            //        if the BMI is less than 18.5 and greater than 24.9 , then
        } else if (intbmi<18.5 && intbmi>24.9) {
            //            setting text to TextView as,
            bmiCategory.setText("Optimum Range");
          //  background.setBackgroundColor(Color.BLUE);
            imgDisplay.setImageResource(R.drawable.normal);
            //        if the BMI is less than 25 and , greater than 29.9, then
        }else if (intbmi<25 && intbmi>29.9) {
            //            setting text to TextView as,
            bmiCategory.setText("Overweight");
         //   background.setBackgroundColor(Color.YELLOW);
            imgDisplay.setImageResource(R.drawable.overweight);
            //        if the BMI is less than 30 and greater 34.9, then
        }else if (intbmi<30 && intbmi>34.9) {
            //            setting text to TextView as,
            bmiCategory.setText("Obese");
           // background.setBackgroundColor(Color.GREEN );
            imgDisplay.setImageResource(R.drawable.obese);
        }
//        else if (intbmi<35 && intbmi>39.9) {
//            bmiCategory.setText("Extremly Obese");
//          //  background.setBackgroundColor(getResources().getColor(R.color.green));
//            imgDisplay.setImageResource(R.drawable.extremly_obese);
//        }
//        //     else, if the BMI is less 40kg, then
        else {
            //            setting text to TextView as,
            bmiCategory.setText("Extremly Obese");

//            for setting background of "background" layout
           // background.setBackgroundColor(getResources().getColor(R.color.red));
//            setting image resource on imgDisplay ImageView
            imgDisplay.setImageResource(R.drawable.extremly_obese);
        }

//        getting Gender form the MainActivity and set it on the txtgenderDisplay TextView
        txtgenderDisplay.setText(intent.getStringExtra("gender"));
//        calculating BMI and set it on txtbmiDisplay TextView
        txtbmiDisplay.setText(bmi);

//       Clicked listener on recalculateBMIBtn Button
        recalculateBMIBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               //creating instance of the Intent class, for the Navigation between ShowBMI Activity and MainActivity
               Intent intent = new Intent(ShowBMI.this,MainActivity.class);
               //calling startActivity method here
               startActivity(intent);
               //lastly calling finished method to destroy ShowBMI Activity
               finish();
           }
       });
    }

//    if user back press on his device, then we navigate it to MainActivity
    @Override
    public void onBackPressed() {
        super.onBackPressed();

//        on the back presses of the user, we jumped to the MainActivity.java
        startActivity(new Intent(ShowBMI.this, MainActivity.class));
    }
}