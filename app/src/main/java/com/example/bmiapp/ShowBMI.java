package com.example.bmiapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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

       // Objects.requireNonNull(getSupportActionBar()).hide();

        TextView txtbmiDisplay = findViewById(R.id.bmiDisplay);
        TextView txtgenderDisplay = findViewById(R.id.genderDisplytxt);
        TextView bmiCategory = findViewById(R.id.bmiCategory);

        ImageView imgDisplay = findViewById(R.id.resultImg);
        RelativeLayout background = findViewById(R.id.contentLayout);

        Button button =findViewById(R.id.recalculateBMIBtn);


//          getSupportActionBar().setElevation(0);
//          getSupportActionBar().setTitle(Html.fromHtml("<font color=\"white\"></font>"));
//          getSupportActionBar().setTitle("Result");
//        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#ED2727"));
//        getSupportActionBar().setBackgroundDrawable(colorDrawable);

        Intent intent;
        intent = getIntent();

       height = intent.getStringExtra("height");
      weight =  intent.getStringExtra("weight");

         intHeight = Float.parseFloat(height);
         intWeight = Float.parseFloat(weight);

        intHeight = intHeight/100;

        intbmi = intWeight/(intHeight*intHeight);

        bmi = Float.toString(intbmi);

        if (intbmi<16){
            bmiCategory.setText("Severe Thiness");
           // background.setBackgroundColor(Color.RED);
            imgDisplay.setImageResource(R.drawable.thinness);
        } else if (intbmi<16.9 && intbmi>16) {
            bmiCategory.setText("Moderate Thinness");
          //  background.setBackgroundColor(Color.BLUE);
            imgDisplay.setImageResource(R.drawable.moderatethin);
        }else if (intbmi<18.4 && intbmi>17) {
            bmiCategory.setText("Mile Thinness");
         //   background.setBackgroundColor(Color.YELLOW);
            imgDisplay.setImageResource(R.drawable.milethine);
        }else if (intbmi<25 && intbmi>18.4) {
            bmiCategory.setText("Normal");
           // background.setBackgroundColor(Color.GREEN );
            imgDisplay.setImageResource(R.drawable.normal);
        }else if (intbmi<29.4 && intbmi>25) {
            bmiCategory.setText("OverWeight");
          //  background.setBackgroundColor(getResources().getColor(R.color.green));
            imgDisplay.setImageResource(R.drawable.obesity);
        }else {
            bmiCategory.setText("Obese Class I");
           // background.setBackgroundColor(getResources().getColor(R.color.red));
            imgDisplay.setImageResource(R.drawable.obesity);
        }

        txtgenderDisplay.setText(intent.getStringExtra("gender"));
        txtbmiDisplay.setText(bmi);

        button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(ShowBMI.this,MainActivity.class);
               startActivity(intent);
               finish();
           }
       });
    }
}