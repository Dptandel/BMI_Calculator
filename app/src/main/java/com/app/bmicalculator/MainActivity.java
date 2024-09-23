package com.app.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    android.widget.Button calculateBMI;

    TextView currentHeight, currentWeight, currentAge;
    ImageView decWeight, incWeight, decAge, incAge;
    SeekBar seekBarForHeight;
    RelativeLayout male, female;

    int intWeight = 55;
    int intAge = 22;
    int currentProgress;
    String intProgress = "170";
    String typeOfUser = "0";
    String weight2 = "55";
    String age2 = "22";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Window window = MainActivity.this.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.black));
        }

        calculateBMI = findViewById(R.id.calculateBMI);

        currentHeight = findViewById(R.id.currentHeight);
        currentWeight = findViewById(R.id.currentWeight);
        currentAge = findViewById(R.id.currentAge);
        decWeight = findViewById(R.id.decWeight);
        incWeight = findViewById(R.id.incWeight);
        decAge = findViewById(R.id.decAge);
        incAge = findViewById(R.id.incAge);
        seekBarForHeight = findViewById(R.id.seekBarForHeight);
        male = findViewById(R.id.male);
        female = findViewById(R.id.female);

        male.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                male.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.male_female_focus));
                female.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.male_female_not_focus));
                typeOfUser = "Male";
            }
        });

        female.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                female.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.male_female_focus));
                male.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.male_female_not_focus));
                typeOfUser = "Female";
            }
        });

        seekBarForHeight.setMax(300);
        seekBarForHeight.setProgress(170);
        seekBarForHeight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                currentProgress = progress;
                intProgress = String.valueOf(currentProgress);
                currentHeight.setText(intProgress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        incAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intAge = intAge + 1;
                age2 = String.valueOf(intAge);
                currentAge.setText(age2);
            }
        });

        decAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intAge = intAge - 1;
                age2 = String.valueOf(intAge);
                currentAge.setText(age2);
            }
        });

        incWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intWeight = intWeight + 1;
                weight2 = String.valueOf(intWeight);
                currentWeight.setText(weight2);
            }
        });

        decWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intWeight = intWeight - 1;
                weight2 = String.valueOf(intWeight);
                currentWeight.setText(weight2);
            }
        });

        calculateBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(typeOfUser.equals("0")){
                    Toast.makeText(MainActivity.this, "Select Your Gender First", Toast.LENGTH_SHORT).show();
                } else if (intProgress.equals("0")){
                    Toast.makeText(MainActivity.this, "Select Your Height First", Toast.LENGTH_SHORT).show();
                } else if (intAge==0 || intAge<0){
                    Toast.makeText(MainActivity.this, "Age is incorrect", Toast.LENGTH_SHORT).show();
                } else if (intWeight==0 || intWeight<0){
                    Toast.makeText(MainActivity.this, "Weight is incorrect", Toast.LENGTH_SHORT).show();
                } else {
                    Intent BMI = new Intent(MainActivity.this, BMI.class);
                    BMI.putExtra("gender", typeOfUser);
                    BMI.putExtra("height",intProgress);
                    BMI.putExtra("weight",weight2);
                    BMI.putExtra("age",age2);
                    startActivity(BMI);
                }
            }
        });
    }
}