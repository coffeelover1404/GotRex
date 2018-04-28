package com.example.nansi.gotrextest2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class GotRexAdult extends Activity {

    private GotRexDatabase gotRexDatabase;
    Button newGameBtn;

    ImageView imageView;
    AnimationDrawable anim;

    Bitmap b;
    Button cameraBtn;
    ImageView capturedImg, darkBg;
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gotrex_adult);

        gotRexDatabase = new GotRexDatabase(this);
        gotRexDatabase.open();
        newGameBtn = findViewById(R.id.newGame);

        newGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if user click play again, delete database
                gotRexDatabase.deleteGotRex();
                Intent startGame = new Intent(GotRexAdult.this, NewLauncher.class);
                startActivity(startGame);
            }
        });
        boolean  checkAdult = gotRexDatabase.checkBond(); //check bond score

        imageView = (ImageView) findViewById(R.id.adult);
        if(imageView == null) throw new AssertionError();

        if(checkAdult == true){
            //true means the bond score is more than 200 -> get trex
            imageView.setBackgroundResource(R.drawable.animation_trex);
        }
        else{
            //get godzilla
            imageView.getLayoutParams().width = 1000;
            imageView.getLayoutParams().height = 1200;
            imageView.setBackgroundResource(R.drawable.animation_godzilla);
        }
        anim = (AnimationDrawable)imageView.getBackground();
        anim.start();


        capturedImg = (ImageView) findViewById(R.id.imageView);
        darkBg = (ImageView) findViewById(R.id.dark);
        cameraBtn = (Button) findViewById(R.id.cameraa);

        cameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt = (TextView) findViewById(R.id.path);
                b = CaptureScreen.rootViewShot(v);
                darkBg.setBackgroundColor(Color.parseColor("#33333300"));
                capturedImg.setImageBitmap(b);
                CaptureScreen.saveImage(b, "Gotrex");
                txt.setText("image saved");

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        capturedImg.setImageBitmap(null);
                        darkBg.setBackgroundColor(Color.parseColor("#00000000"));
                        txt.setText("");
                    }
                }, 1000);

            }
        });


    }
}
