package com.example.yurynoh.myapplication;

import android.graphics.Color;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    Switch toggleBookTime;
    Chronometer tickBookTime;
    LinearLayout membersBook, timeBook;
    RadioGroup firstRG, secondRG;
    ImageView imageView;
    int billMethod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toggleBookTime = (Switch)findViewById(R.id.switch1);
        tickBookTime = (Chronometer)findViewById(R.id.chronometer2);
        membersBook = (LinearLayout)findViewById(R.id.member);
        timeBook = (LinearLayout)findViewById(R.id.time);
        firstRG = (RadioGroup)findViewById(R.id.radioGroup1);
        imageView = (ImageView)findViewById(R.id.imageView);

        toggleBookTime.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    membersBook.setVisibility(View.VISIBLE);
                    tickBookTime.setBase(SystemClock.elapsedRealtime());
                    tickBookTime.start();
                    tickBookTime.setTextColor(Color.BLUE);
                } else {
                    membersBook.setVisibility(View.INVISIBLE);
                    tickBookTime.stop();
                    tickBookTime.setTextColor(Color.BLACK);
                }
            }
        });

        firstRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.radioButton3) {
                    imageView.setImageResource(R.drawable.image1);
                    billMethod = 1;
                } else if(checkedId == R.id.radioButton2) {
                    imageView.setImageResource(R.drawable.image2);
                    billMethod = 2;
                } else if(checkedId == R.id.radioButton) {
                    imageView.setImageResource(R.drawable.image3);
                    billMethod = 3;
                }
            }
        });
    }
}
