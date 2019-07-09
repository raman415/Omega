package com.newproject.omega;

import android.app.Activity;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {

    private FirebaseAuth mySplashfirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        // initailizing FirebaseAuth API or like allocating memory to it's reference
        mySplashfirebaseAuth = FirebaseAuth.getInstance();
        SystemClock.sleep(1500);

    }
   //
    @Override
    protected void onStart() {
        super.onStart();
        // check if someone is already signIN/SignUp or not
        FirebaseUser currentUser = mySplashfirebaseAuth.getCurrentUser();

        if(currentUser == null){
            Intent loginIntent = new Intent(SplashActivity.this,RegisterActivity.class);
            startActivity(loginIntent);
            finish();
        }else{
            Intent enterMainIntent = new Intent(SplashActivity.this,MainActivity.class);
            startActivity(enterMainIntent);
            finish();

        }
    }
}
