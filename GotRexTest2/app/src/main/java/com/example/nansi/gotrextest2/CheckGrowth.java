package com.example.nansi.gotrextest2;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;

public class CheckGrowth {

    //this class will be used in every fragments to check whether the growth status is full or not

    //getGrowth method will receive boolean from the method in database
    //also receive activity to build dialog and call new activity

    public static void getGrowth(boolean checkGrowth, final Activity act){
        if(checkGrowth == true){
            AlertDialog.Builder mBuilder = new AlertDialog.Builder(act);
            mBuilder.setMessage("Now your baby has grown up. Let's see what it will become!").setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //start new activity to show adult
                            Intent endGame = new Intent(act, GotRexAdult.class);
                            act.startActivity(endGame);
                        }
                    });
            AlertDialog alert = mBuilder.create();
            alert.setTitle("Congratulation");
            alert.show();
        }
    }

}
