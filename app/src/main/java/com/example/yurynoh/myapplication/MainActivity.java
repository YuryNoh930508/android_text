package com.example.yurynoh.myapplication;

import android.graphics.Color;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Switch toggleBookTime;
    Chronometer tickBookTime;
    LinearLayout membersBook, timeBook;
    RadioGroup firstRG, secondRG;
    ImageView imageView;
    Button memberBook, timeBookGo, timesBook, memberBookGo;
    EditText mature, premature, kid;
    TextView totalMember, discount, totalPrice;
    CalendarView calendarView;
    TimePicker timePicker;
    int billMethod, year, month, day, hour, min;
    boolean memberBookDone;

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
        memberBook = (Button)findViewById(R.id.button3);
        timeBookGo =  (Button)findViewById(R.id.button2);
        mature = (EditText)findViewById(R.id.editText);
        premature = (EditText)findViewById(R.id.editText2);
        kid = (EditText)findViewById(R.id.editText3);
        totalMember = (TextView)findViewById(R.id.textView8);
        discount = (TextView)findViewById(R.id.textView10);
        totalPrice = (TextView)findViewById(R.id.textView12);
        billMethod = 1;
        memberBookDone = false;

        secondRG = (RadioGroup)findViewById(R.id.radioGroup2);
        calendarView = (CalendarView)findViewById(R.id.calendarView);
        timePicker = (TimePicker)findViewById(R.id.timePicker);
        year = -1; month = -1; day = -1; hour = -1; min = -1;
        timesBook = (Button)findViewById(R.id.button7);
        memberBookGo = (Button)findViewById(R.id.button6);



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
                    timeBook.setVisibility(View.INVISIBLE);
                    tickBookTime.stop();
                    tickBookTime.setTextColor(Color.BLACK);
                    billMethod = 1;
                    memberBookDone = false;
                    year = -1; month = -1; day = -1; hour = -1; min = -1;
                    calendarView.clearFocus();
                    timePicker.clearFocus();
                    firstRG.check(R.id.radioButton3);
                    secondRG.check(R.id.radioButton7);
                    mature.setText("");
                    premature.setText("");
                    kid.setText("");
                    totalMember.setText("");
                    discount.setText("");
                    totalPrice.setText("");
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

        memberBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(mature.getText()) && TextUtils.isEmpty(premature.getText()) && TextUtils.isEmpty(kid.getText())) {
                    Toast.makeText(MainActivity.this, "인원을 제대로 입력하세요", Toast.LENGTH_SHORT).show();
                } else {
                    int matureCnt = Integer.parseInt(mature.getText().toString());
                    int prematureCnt = Integer.parseInt(premature.getText().toString());
                    int kidCnt = Integer.parseInt(kid.getText().toString());

                    totalMember.setText((matureCnt+prematureCnt+kidCnt) + " 명");

                    int price = (matureCnt *15000) + (prematureCnt * 12000) + (kidCnt * 6000);
                    double discountAmt = 0;
                    if(billMethod == 1) discountAmt = price * 0.05;
                    else if(billMethod == 2) discountAmt = price * 0.1;
                    else if(billMethod == 3) discountAmt = price * 0.2;

                    discount.setText(discountAmt + " 원");
                    totalPrice.setText((price - discountAmt) +" 원");
                    memberBookDone = true;
                }
            }
        });

        timeBookGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(memberBookDone) {
                    membersBook.setVisibility(View.INVISIBLE);
                    timeBook.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(MainActivity.this, "인원 예약을 마치세요", Toast.LENGTH_SHORT).show();
                }
            }
        });

        secondRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.radioButton7) {
                    calendarView.setVisibility(View.VISIBLE);
                    timePicker.setVisibility(View.INVISIBLE);
                } else if(checkedId == R.id.radioButton6) {
                    timePicker.setVisibility(View.VISIBLE);
                    calendarView.setVisibility(View.INVISIBLE);
                }
            }
        });

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int y, int m, int d) {
                year = y; month = m; day = d;
            }
        });

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int h, int m) {
                hour = h; min = m;
            }
        });

        timesBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(year == -1 || hour == -1) {
                    Toast.makeText(MainActivity.this, "날짜 혹은 시간을 제대로 입력하세요", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, year+"-"+month+"-"+day+"-"+hour+":"+min+" 예약이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        memberBookGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeBook.setVisibility(View.INVISIBLE);
                membersBook.setVisibility(View.VISIBLE);
            }
        });
    }
}
