package com.cookandroid.firebaseproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

public class memo2 extends AppCompatActivity {


    private EditText mTitleEditText;
    private EditText mContentEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo2);
        setTitle("pengsoo_memo");

        mTitleEditText =(EditText)findViewById(R.id.title_edit);
        mContentEditText =(EditText)findViewById(R.id.content_edit);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_memo2,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.action_save:
                save();
                return true;
            case R.id.action_cancel:
                cancel();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private void cancel() {
        setResult(RESULT_CANCELED);
        finish();
    }

    private  void save(){
        Intent intent = new Intent();
        intent.putExtra("title",mTitleEditText.getText().toString());
        intent.putExtra("content",mContentEditText.getText().toString());
        setResult(RESULT_OK,intent);
        finish();
    }
}