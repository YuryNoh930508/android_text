package com.example.yurynoh.myapplication;

import android.graphics.Color;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    Switch toggleBookTime;
    Chronometer tickBookTime;
    LinearLayout membersBook, timeBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toggleBookTime = (Switch)findViewById(R.id.switch1);
        tickBookTime = (Chronometer)findViewById(R.id.chronometer2);
        membersBook = (LinearLayout)findViewById(R.id.member);
        timeBook = (LinearLayout)findViewById(R.id.time);

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
    }
}
