package com.cookandroid.firebaseproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MusicMain extends AppCompatActivity {

    ArrayList<Song> al = new ArrayList<Song>();  //  곡명을 담을 리스트

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_music_main);
        setTitle("pengsoo_music_list");

        al.add(new Song("참치송",R.drawable.naeun,"손나은 & 펭수"));
        al.add(new Song("펭수로 하겠습니다",R.drawable.pensoorotrue,"타이거jk & 비지 & 펭수"));
        al.add(new Song("신이나",R.drawable.img01,"신난펭수"));
        al.add(new Song("요들송",R.drawable.img02,"펭수"));
        al.add(new Song("남극참치",R.drawable.img03,"남극펭수"));
        al.add(new Song("자이언트",R.drawable.img04,"펭수"));
        al.add(new Song("크리스마스",R.drawable.img05,"산타펭수"));
       // al.add(new Song("청춘 (feat. 김창완)",R.drawable.img06,"김필(Kim Feel"));
       // al.add(new Song("Hot Pink",R.drawable.img07,"EXID(이엑스아이디)"));


        MyAdapter2 adapter = new MyAdapter2(
                getApplicationContext(), // 현재화면의 제어권자
                R.layout.row,  // 리스트뷰의 한행의 레이아웃
                al);         // 데이터

        ListView lv = (ListView)findViewById(R.id.listView1);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // 상세정보 화면으로 이동하기(인텐트 날리기)
                // 1. 다음화면을 만든다
                // 2. AndroidManifest.xml 에 화면을 등록한다
                // 3. Intent 객체를 생성하여 날린다
                Intent intent = new Intent(
                        getApplicationContext(), // 현재화면의 제어권자
                        SongDetail.class); // 다음넘어갈 화면

                // intent 객체에 데이터를 실어서 보내기
                // 리스트뷰 클릭시 인텐트 (Intent) 생성하고 position 값을 이용하여 인텐트로 넘길값들을 넘긴다

                intent.putExtra("title", al.get(position).title);
                intent.putExtra("img", al.get(position).img);
                intent.putExtra("artist", al.get(position).artist);

                startActivity(intent);
            }
        });
    }
}

class MyAdapter2 extends BaseAdapter { // 리스트 뷰의 아답타
    Context context;
    int layout;
    ArrayList<Song> al;
    LayoutInflater inf;
    public MyAdapter2(Context context, int layout, ArrayList<Song> al) {
        this.context = context;
        this.layout = layout;
        this.al = al;
        inf = (LayoutInflater)context.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return al.size();
    }
    @Override
    public Object getItem(int position) {
        return al.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null) {
            convertView = inf.inflate(layout, null);
        }
        ImageView iv = (ImageView)convertView.findViewById(R.id.imageView1);
        TextView tvName = (TextView)convertView.findViewById(R.id.textView1);
        TextView tvInfo = (TextView)convertView.findViewById(R.id.textView2);

        Song m = al.get(position);
        iv.setImageResource(m.img);
        tvName.setText(m.title);
        tvInfo.setText(m.artist);

        return convertView;
    }
}

class Song { // 자바빈
    String title = ""; // 곡 title
    int img; // 앨범 이미지
    String artist = ""; // artist
    public Song(String title, int img, String artist) {
        super();
        this.title = title;
        this.img = img;
        this.artist = artist;
    }
    public Song() {}
}