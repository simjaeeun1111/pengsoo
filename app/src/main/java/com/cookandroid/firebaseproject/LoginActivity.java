package com.cookandroid.firebaseproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private EditText mEmail,mPassword;
    private Button btnstart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("pengsoo_login");


        mEmail = findViewById(R.id.login_email);
        mPassword = findViewById(R.id.login_password);
        btnstart =(Button)findViewById(R.id.btn_startApp);

        findViewById(R.id.login_singup).setOnClickListener(this);
        findViewById(R.id.login_success).setOnClickListener(this);
    }

    //자동로그인코드
    protected  void onStart(){
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if(user !=null){
            Toast.makeText(this,"자동로그인: " + user.getUid(),Toast.LENGTH_LONG).show();
            btnstart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(LoginActivity.this, AppFirstActivity.class);
                    startActivity(intent);
                }
            });

        }
    }


    @Override
    public void onClick(View v) {
        switch( v.getId()){
            case R.id. login_singup:
                startActivity(new Intent(this,SignupActivity.class));
                break;

            case R.id. login_success:
                mAuth.signInWithEmailAndPassword(mEmail.getText().toString(),mPassword.getText().toString())
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                   FirebaseUser user = mAuth.getCurrentUser();
                                        if(user!= null)
                                        {Toast.makeText(LoginActivity.this, "login 성공: " + user.getUid(), Toast.LENGTH_SHORT).show();
                                            btnstart.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    Intent intent = new Intent(LoginActivity.this, AppFirstActivity.class);
                                                    startActivity(intent);
                                                }
                                            });
                                    }
                                } else {
                                    Toast.makeText(LoginActivity.this, "login error.", Toast.LENGTH_SHORT).show();
                                }
                                // ...
                            }
                        });
                break;
        }
    }
}
