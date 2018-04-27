package com.example.nansi.gotrextest2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;


public class CrackEgg extends Activity {

    ImageView imageView;
    AnimationDrawable anim;
    private static int SPLASH_TIME_OUT = 4000;  // duration for the welcome screen appearance
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crack_egg);

        // move to home screen (HomeFragment) after the egg cracked (4 sec.)
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent eggWait = new Intent(CrackEgg.this, MainActivity.class);
                startActivity(eggWait);
                finish();
            }
        }, SPLASH_TIME_OUT);

        //initial the animation setting
        imageView = findViewById(R.id.imageEgg);
        if(imageView == null) throw new AssertionError();
        imageView.setBackgroundResource(R.drawable.animation_egg);

        //play egg animation
        anim = (AnimationDrawable)imageView.getBackground();
        anim.start();
    }
}
