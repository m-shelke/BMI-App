package com.example.bmiapp;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.window.OnBackInvokedDispatcher;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Objects;

public class ShowBMI extends AppCompatActivity {

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


        TextView txtbmiDisplay = findViewById(R.id.bmiDisplay);
        TextView txtgenderDisplay = findViewById(R.id.genderDisplytxt);
        TextView bmiCategory = findViewById(R.id.bmiCategory);

        ImageView imgDisplay = findViewById(R.id.resultImg);
        RelativeLayout background = findViewById(R.id.contentLayout);

        LinearLayout linearLayoutLink1 = findViewById(R.id.link1);

        Button button =findViewById(R.id.recalculateBMIBtn);


          Objects.requireNonNull(getSupportActionBar()).setElevation(0);
          getSupportActionBar().setTitle(Html.fromHtml("<font color=\"white\"></font>"));
          getSupportActionBar().setTitle("Result is..");

        ColorDrawable colorDrawable = new ColorDrawable(getColor(R.color.black));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);

        linearLayoutLink1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShowBMI.this,BasedOn1.class));
            }
        });

        Intent intent;
        intent = getIntent();

       height = intent.getStringExtra("height");
       weight =  intent.getStringExtra("weight");

         intHeight = Float.parseFloat(height);
         intWeight = Float.parseFloat(weight);

        intHeight = intHeight/100;

        intbmi = intWeight/(intHeight*intHeight);

        bmi = Float.toString(intbmi);

        if (intbmi<18.5){
            bmiCategory.setText("Underweight");
           // background.setBackgroundColor(Color.RED);
            imgDisplay.setImageResource(R.drawable.underweight);
        } else if (intbmi<18.5 && intbmi>24.9) {
            bmiCategory.setText("Optimum Range");
          //  background.setBackgroundColor(Color.BLUE);
            imgDisplay.setImageResource(R.drawable.normal);
        }else if (intbmi<25 && intbmi>29.9) {
            bmiCategory.setText("Overweight");
         //   background.setBackgroundColor(Color.YELLOW);
            imgDisplay.setImageResource(R.drawable.overweight);
        }else if (intbmi<30 && intbmi>34.9) {
            bmiCategory.setText("Obese");
           // background.setBackgroundColor(Color.GREEN );
            imgDisplay.setImageResource(R.drawable.obese);
        }
//        else if (intbmi<35 && intbmi>39.9) {
//            bmiCategory.setText("Extremly Obese");
//          //  background.setBackgroundColor(getResources().getColor(R.color.green));
//            imgDisplay.setImageResource(R.drawable.extremly_obese);
//        }
        else {
            bmiCategory.setText("Extremly Obese");
           // background.setBackgroundColor(getResources().getColor(R.color.red));
            imgDisplay.setImageResource(R.drawable.extremly_obese);
        }

        txtgenderDisplay.setText(intent.getStringExtra("gender"));
        txtbmiDisplay.setText(bmi);


        button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               //creating instance of the Intent class, for the Navigation between ShowBMI Activity and MainActivity
               Intent intent = new Intent(ShowBMI.this,MainActivity.class);
               //calling startActivity method here
               startActivity(intent);
               //lastly calling finished method to destroy ShowBMI Activity
              // finish();
           }
       });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

//        on the back presses of the user, we jumped to the MainActivity.java
        startActivity(new Intent(ShowBMI.this, MainActivity.class));
    }
}