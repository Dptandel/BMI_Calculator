package com.app.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class BMI extends AppCompatActivity {

    android.widget.Button recalculateBMI;

    TextView bmiDisplay, bmiCategory, gender;
    Intent BMI;
    ImageView imageView;
    String bmi;
    float intBMI;

    String height, weight;
    Float intHeight, intWeight;
    RelativeLayout background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        Window window = BMI.this.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(BMI.this, R.color.black));
        }

        recalculateBMI = findViewById(R.id.recalculateBMI);

        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"white\"></font>"));
        getSupportActionBar().setTitle("Result");
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#1E1D1D"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);

        BMI = getIntent();
        bmiDisplay = findViewById(R.id.bmiDisplay);
        bmiCategory = findViewById(R.id.bmiCategory);
        gender = findViewById(R.id.genderDisplay);
        background = findViewById(R.id.contentLayout);
        imageView = findViewById(R.id.imageView);
        recalculateBMI = findViewById(R.id.recalculateBMI);

        height = BMI.getStringExtra("height");
        weight = BMI.getStringExtra("weight");

        intHeight = Float.parseFloat(height);
        intWeight = Float.parseFloat(weight);

        intHeight = intHeight / 100;

        intBMI = intWeight / (intHeight * intHeight);

        bmi = Float.toString(intBMI);

        if (intBMI < 16) {
            bmiCategory.setText("Severe Thinness");
            background.setBackgroundColor(Color.parseColor("#FEFB01"));
            imageView.setImageResource(R.drawable.warning);
        } else if (intBMI < 17 && intBMI > 16) {
            bmiCategory.setText("Moderate Thinness");
            background.setBackgroundColor(Color.parseColor("#CEFB02"));
            imageView.setImageResource(R.drawable.warning);
        } else if (intBMI < 18.5 && intBMI > 17) {
            bmiCategory.setText("Mild Thinness");
            background.setBackgroundColor(Color.parseColor("#87FA00"));
            imageView.setImageResource(R.drawable.warning);
        } else if (intBMI < 25 && intBMI > 18.5) {
            bmiCategory.setText("Normal");
            background.setBackgroundColor(Color.parseColor("#3AF901"));
            imageView.setImageResource(R.drawable.ok);
        } else if (intBMI < 30 && intBMI > 25) {
            bmiCategory.setText("Overweight");
            background.setBackgroundColor(Color.parseColor("#F62217"));
            imageView.setImageResource(R.drawable.crosss);
        } else if (intBMI < 35 && intBMI > 30) {
            bmiCategory.setText("Obese Class I");
            background.setBackgroundColor(Color.parseColor("#FF2400"));
            imageView.setImageResource(R.drawable.crosss);
        } else if (intBMI < 40 && intBMI > 35) {
            bmiCategory.setText("Obese Class II");
            background.setBackgroundColor(Color.parseColor("#FD1C03"));
            imageView.setImageResource(R.drawable.crosss);
        } else {
            bmiCategory.setText("Obese Class III");
            background.setBackgroundColor(Color.parseColor("#FF0000"));
            imageView.setImageResource(R.drawable.crosss);
        }

        gender.setText(BMI.getStringExtra("gender"));
        bmiDisplay.setText(bmi);
        recalculateBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BMI.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}