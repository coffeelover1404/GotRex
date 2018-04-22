package com.example.nansi.gotrextest2;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;

public class CheckGrowth {

    public static void getGrowth(boolean checkGrowth, final Activity act){
        if(checkGrowth == true){
            AlertDialog.Builder mBuilder = new AlertDialog.Builder(act);
            mBuilder.setMessage("Now your baby become an adult, let's see what it will become!").setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent endGame = new Intent(act, GotRexAdult.class);
                            act.startActivity(endGame);
                        }
                    });
            AlertDialog alert = mBuilder.create();
            alert.setTitle("Success!");
            alert.show();
        }
    }

}
