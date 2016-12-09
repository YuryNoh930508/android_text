package com.example.yurynoh.myapplication;

import android.graphics.Color;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Switch toggleBookTime;
    Chronometer tickBookTime;
    LinearLayout membersBook, timeBook;
    RadioGroup firstRG, secondRG;
    ImageView imageView;
    Button memberBook, timeBookGo;
    EditText mature, premature, kid;
    TextView totalMember, discount, totalPrice;
    int billMethod;
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

        memberBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(mature.getText()) && TextUtils.isEmpty(premature.getText()) && TextUtils.isEmpty(kid.getText())) {
                    Toast.makeText(MainActivity.this, "인원을 입력하세요", Toast.LENGTH_SHORT).show();
                } else {
                    int matureCnt = Integer.parseInt(mature.getText().toString());
                    int prematureCnt = Integer.parseInt(premature.getText().toString());
                    int kidCnt = Integer.parseInt(kid.getText().toString());

                    totalMember.setText((matureCnt+prematureCnt+kidCnt) + "명");

                    int price = (matureCnt *15000) + (prematureCnt * 12000) + (kidCnt * 6000);
                    double discountAmt = 0;
                    if(billMethod == 1) discountAmt = price * 0.05;
                    else if(billMethod == 2) discountAmt = price * 0.1;
                    else if(billMethod == 3) discountAmt = price * 0.2;

                    discount.setText(discountAmt + "원");
                    totalPrice.setText((price - discountAmt) +"원");
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
    }
}
