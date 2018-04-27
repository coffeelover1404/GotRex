package com.example.nansi.gotrextest2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class GotRexAdult extends Activity {

    private GotRexDatabase gotRexDatabase;
    Button newGameBtn;

    ImageView imageView;
    AnimationDrawable anim;
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

    }
}
