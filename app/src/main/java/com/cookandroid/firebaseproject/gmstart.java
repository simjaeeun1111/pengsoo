package com.cookandroid.firebaseproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class gmstart extends AppCompatActivity {

    private TextView scoreLabel;
    private TextView startLabel;
    private ImageView box;
    private ImageView ice;
    private ImageView tuna;
    private ImageView fire;

    //크기
    private int frameHeight;
    private int boxSize;
    private int screenWidth;
    private int screenHeight;


    //위치
    private int boxY;
    private int iceX;
    private int iceY;
    private int tunaX;
    private int tunaY;
    private int fireX;
    private int fireY;

    //점수 초기화
    private int score = 0;


    //핸들러
    private Handler handler = new Handler();
    private Timer timer = new Timer();

    //액션제어 변수 초기화
    private boolean action_flg = false;
    private boolean start_flg = false;

    //음악 넣기
    private MediaPlayer mp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gmstart);
        setTitle("pengsoo_game");


        Intent intent = getIntent(); /*데이터 수신*/

        scoreLabel = (TextView) findViewById(R.id.scoreLabel);
        startLabel = (TextView) findViewById(R.id.startLabel);
        box = (ImageView) findViewById(R.id.character);
        ice = (ImageView) findViewById(R.id.ice);
        tuna = (ImageView) findViewById(R.id.feed);
        fire = (ImageView) findViewById(R.id.fire);

        //화면 크기 가져오기
        WindowManager wm = getWindowManager();
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);

        screenWidth = size.x;
        screenHeight = size.y;


        //처음 시작할때 그 아이템들이 안보여야 하므로 이
        ice.setX(-80);
        ice.setY(-80);
        tuna.setX(-80);
        tuna.setY(-80);
        fire.setX(-80);
        fire.setY(-80);

        scoreLabel.setText("Score : 0");
        //일시??
    }

    public void changePos() {

        hitCheck();

        //ice
        iceX -= 12;
        if (iceX < 0) {
            iceX = screenWidth + 20;
            iceY = (int) Math.floor(Math.random() * (frameHeight - ice.getHeight()));
        }
        ice.setX(iceX);
        ice.setY(iceY);

        //fire
        fireX -= 16;
        if (fireX < 0) {
            fireX = screenWidth + 10;
            fireY = (int) Math.floor(Math.random() * (frameHeight - fire.getHeight()));
        }
        fire.setX(fireX);
        fire.setY(fireY);

        //참치
        tunaX -= 20;
        if (tunaX < 0) {
            tunaX = screenWidth + 5000;
            tunaY = (int) Math.floor(Math.random() * (frameHeight - tuna.getHeight()));
        }
        tuna.setX(tunaX);
        tuna.setY(tunaY);

        // Box == 여기서는 펭수임
        if (action_flg == true) {
            //Touching
            boxY -= 20;
        } else {
            //Releasing
            boxY += 20;
        }



        //화면안에 박스가 넘어가면 안되므로
        if (boxY < 0) boxY = 0;
        if (boxY > frameHeight - boxSize) boxY = frameHeight - boxSize;

        box.setY(boxY);
        scoreLabel.setText("Score : " + score);
    }

    public void hitCheck() {


        //아이템이 맞을떄 중심에 맞아야함
        int iceCenterX = iceX + ice.getWidth() / 2;
        int iceCenterY = iceY + ice.getHeight() / 2;


        if (0 <= iceCenterX && iceCenterX <= boxSize &&
                boxY <= iceCenterY && iceCenterY <= boxY + boxSize) {
            score += 10;
            iceX = -10;
        }


        int tunaCenterX = tunaX + tuna.getWidth() / 2;
        int tunaCenterY = tunaY + tuna.getHeight() / 2;

        if (0 <= tunaCenterX && tunaCenterX <= boxSize &&
                boxY <= tunaCenterY && tunaCenterY <= boxY + boxSize) {
            mp = MediaPlayer.create(getApplicationContext(),R.raw.pengpeng);
            mp.start();
            score += 30;
            tunaX = -10;
        }


        int fireCenterX = fireX + fire.getWidth() / 2;
        int fireCenterY = fireY + fire.getHeight() / 2;

        if (0 <= fireCenterX && fireCenterX <= boxSize &&
                boxY <= fireCenterY && fireCenterY <= boxY + boxSize) {

            //   mp = MediaPlayer.create(getApplicationContext(),R.raw.sad);
            // mp.start();
            //stop timer

            timer.cancel();
            timer = null;
            mp=null;

            //Show Intent
            Intent intent = new Intent(getApplicationContext(), gmresult.class);
            intent.putExtra("SCORE", score);
            startActivity(intent);
        }


    }

    public boolean onTouchEvent(MotionEvent me) {
        if (start_flg == false) {

            start_flg = true;

         //onCreate 화면에서 ui가 설정되어 있지 않아 프레임 높이와 상자 높이를 얻음

            FrameLayout frame = (FrameLayout) findViewById(R.id.frame);
            frameHeight = frame.getHeight();

            boxY = (int) box.getY();

            boxSize = box.getHeight();


            startLabel.setVisibility(View.GONE);

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            changePos();
                        }
                    });
                }
            }, 0, 20);

        } else {
            if (me.getAction() == MotionEvent.ACTION_DOWN) {
                action_flg = true;
            } else if (me.getAction() == MotionEvent.ACTION_UP) {
                action_flg = false;
            }
        }
        return true;
    }
}
