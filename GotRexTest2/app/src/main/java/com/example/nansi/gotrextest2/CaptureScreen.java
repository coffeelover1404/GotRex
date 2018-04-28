package com.example.nansi.gotrextest2;

import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Random;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by ASUS on 28/4/2561.
 */

public class CaptureScreen {

    public static void saveImage(Bitmap finalBitmap, String image_name) {
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
