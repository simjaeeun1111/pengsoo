package com.cookandroid.firebaseproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainMemo extends AppCompatActivity {

    Button btn_save;

    private static final String TAG = MainMemo.class.getSimpleName();
    public static final int REQUEST_CODE_NEW_MEMO =1000;

    private List<Memo> mMemoList;
    private MemoAdapter mAdapter;
    private ListView mMemoListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_memo);
        setTitle("pengsoo_memo");


        mMemoListView = (ListView)findViewById(R.id.memo_list);

        btn_save = (Button)findViewById(R.id.btn_save);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(MainMemo.this,memo2.class);
                startActivityForResult(intent,REQUEST_CODE_NEW_MEMO);
            }
        });
        //데이터
        mMemoList = new ArrayList<>();
        mAdapter = new MemoAdapter(mMemoList);

        mMemoListView.setAdapter(mAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode ==REQUEST_CODE_NEW_MEMO  ){
            if(resultCode == RESULT_OK){
                String title = data.getStringExtra("title");
                String content = data.getStringExtra("content");

                mMemoList.add(new Memo(title,content));
                mAdapter.notifyDataSetChanged();

                Log.d(TAG,"onActivityResult"+ title + ", "+content);
                Toast.makeText(this,"저장되었습니다.",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(this,"취소 되었습니다.",Toast.LENGTH_LONG).show();
            }
        }
    }
}
