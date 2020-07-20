package com.cookandroid.firebaseproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AppFirstActivity extends AppCompatActivity {

    private Button btn1,btn2,btn3,btn4,btn5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_first);
        setTitle("pengsoo");

        btn1 = (Button)findViewById(R.id.btn1); //갤러리 보기
        btn2 = (Button)findViewById(R.id.btn2);
        btn3 = (Button)findViewById(R.id.btn3); //미니게임
        btn4 = (Button)findViewById(R.id.btn4); //메모
        btn5 = (Button)findViewById(R.id.btn5); //종료

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AppFirstActivity.this,btn1.class);
                startActivity(intent);
            }
        });


        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AppFirstActivity.this,MusicMain.class);
                startActivity(intent);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override //미니게임
            public void onClick(View view) {
                Intent intent = new Intent(AppFirstActivity.this,gmexplain.class);
                startActivity(intent);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(AppFirstActivity.this,MainMemo.class);
                startActivity(intent);
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveTaskToBack(true);
                finish();
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });

    }
}
