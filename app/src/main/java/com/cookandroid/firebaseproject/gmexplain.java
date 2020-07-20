package com.cookandroid.firebaseproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class gmexplain extends AppCompatActivity {

    private Button start_game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gmexplain);
        setTitle("pengsoo_game");


        start_game = (Button)findViewById(R.id.start_game);


        start_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(gmexplain.this,gmstart.class);
                startActivity(intent);
            }
        });
    }
}