package com.example.nansi.gotrextest2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class WelcomeScreen extends Activity {

    private static int SPLASH_TIME_OUT = 1500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent welcome = new Intent(WelcomeScreen.this, NewLauncher.class);
                startActivity(welcome);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}