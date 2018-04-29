package com.example.nansi.gotrextest2;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import java.util.Random;
import java.io.File;
import java.io.FileOutputStream;


public class CaptureScreen {

    public static void saveImage(Bitmap finalBitmap, String image_name, final Activity thisActivity) {
        if (ContextCompat.checkSelfPermission(thisActivity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            //Permission is not granted
            if (ActivityCompat.shouldShowRequestPermissionRationale(thisActivity,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                //Show an explanation to the user
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(thisActivity); // check if player really name the baby
                mBuilder.setMessage("You need to allow Gotrex to access your storage to save a photo \nGo to Setting -> App -> Gotrex").setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alert = mBuilder.create();
                alert.setTitle("Request Permission");
                alert.show();

            } else {
                //request the permission
                ActivityCompat.requestPermissions(thisActivity,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        1);
            }
        }
        else {
            // Permission has already been granted
            int picNum;
            Random randomNum = new Random();
            picNum = 1+randomNum.nextInt(999999);
            String root = Environment.getExternalStorageDirectory().toString(); // get SD card path
            File myDir = new File(root+"/GotrexImages");
            if(!myDir.exists()) myDir.mkdirs();     // create path for keeping Gotrex screenshots
            String fname = image_name + picNum + ".jpg";
            picNum++;
            File file = new File(myDir, fname);
            Log.i("LOAD", root + "/" + fname);
            try {
                FileOutputStream out = new FileOutputStream(file);
                finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);  // compress bitmap to .jpeg file (save image)
                out.flush();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    // Capture screen
    public static Bitmap takeScreenshot(View v) {
        v.setDrawingCacheEnabled(true);
        v.buildDrawingCache(true);
        Bitmap b = Bitmap.createBitmap(v.getDrawingCache());
        v.setDrawingCacheEnabled(false);
        return b;
    }
    public static Bitmap rootViewShot(View v) {
        return takeScreenshot(v.getRootView());
    }
}
