package com.cookandroid.firebaseproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class SongDetail extends AppCompatActivity {
    Button btn_play, btn_stop, btn_pause;
    MediaPlayer mediaplayer;
    private int pausePosition;
    SeekBar seekBar1,seekBar;
    TextView text;
    AudioManager maudioManager = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_detail);
        setTitle("pengsoo_music_play");

        final TextView tvTitle = (TextView) findViewById(R.id.textView1);
        TextView tvArtist = (TextView) findViewById(R.id.textView2);
        ImageView iv = (ImageView) findViewById(R.id.imageView1);

        btn_play = (Button) findViewById(R.id.btn_play);
        //   btn_stop = (Button) findViewById(R.id.btn_stop);
        btn_pause = (Button) findViewById(R.id.btn_pause);
        seekBar1 = (SeekBar) findViewById(R.id.seekBar1);

        //소리
        seekBar= (SeekBar)findViewById(R.id.volumBar1);

        maudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        int maxVol = maudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        //최대치 값을 가져와서 seekBar Max로 하기
        seekBar.setMax(maxVol);
        //  textView.setText("음량최대값: "+  maxVol);


        //Seekbar로 음량 변경
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                //   textView.setText("변환값 "+  i);
                //음악 음량 변경
                maudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, i, AudioManager.FLAG_SHOW_UI);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });




        Intent intent = getIntent(); // 보내온 Intent를 얻는다
        final String str = intent.getStringExtra("title");
        tvTitle.setText(intent.getStringExtra("title"));


        tvArtist.setText(intent.getStringExtra("artist"));
        iv.setImageResource(intent.getIntExtra("img", 0));


        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaplayer == null) {
                    if ("참치송".equals(str)) {
                        mediaplayer = MediaPlayer.create(SongDetail.this, R.raw.tunasong);
                    }
                    if("펭수로 하겠습니다".equals(str)){
                        mediaplayer = MediaPlayer.create(SongDetail.this, R.raw.pengsooro);
                    }
                    if ("신이나".equals(str)) {
                        mediaplayer = MediaPlayer.create(SongDetail.this, R.raw.sinna);
                    }

                    if("요들송".equals(str)){
                        mediaplayer = MediaPlayer.create(SongDetail.this, R.raw.alphs);
                    }
                    if("남극참치".equals(str)){
                        mediaplayer = MediaPlayer.create(SongDetail.this, R.raw.antraticatuna);
                    }
                    if("자이언트".equals(str)){
                        mediaplayer = MediaPlayer.create(SongDetail.this, R.raw.giant);
                    }
                    if("크리스마스".equals(str)){
                        mediaplayer = MediaPlayer.create(SongDetail.this, R.raw.christmas);
                    }

                    // 음악의 총 길이를 시크바 최대값에 적용
                    playClicked();
                    mediaplayer.start();

                } else {
                    mediaplayer.seekTo(pausePosition);
                    playClicked();
                    mediaplayer.start();
                }
            }

            private void playClicked() {

                seekBar1.setMax(mediaplayer.getDuration());  // 음악의 총 길이를 시크바 최대값에 적용
                seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        if(fromUser)  // 사용자가 시크바를 움직이면
                            mediaplayer.seekTo(progress);   // 재생위치를 바꿔준다(움직인 곳에서의 음악재생)
                    }
                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) { }
                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) { }
                });


                new Thread(new Runnable() {  // 쓰레드 생성
                    @Override
                    public void run() {
                        while (mediaplayer.isPlaying()) {  // 음악이 실행중일때 계속 돌아가게 함
                            try {
                                Thread.sleep(1000); // 1초마다 시크바 움직이게 함
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            // 현재 재생중인 위치를 가져와 시크바에 적용
                            seekBar1.setProgress(mediaplayer.getCurrentPosition());
                        }
                    }
                }).start();

            }
        });


//        btn_stop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (mediaplayer.isPlaying()) {
//                    mediaplayer.stop();
//                    mediaplayer = null;
//                }
//            }
//        });

        btn_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaplayer.pause();
                pausePosition = mediaplayer.getCurrentPosition();
                Log.d("pause check", ":" + pausePosition);
            }
        });

    }


}


