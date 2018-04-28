package com.example.nansi.gotrextest2;

//import android.app.FragmentTransaction;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    /*TextView mTextMessage;
    TextView text;*/
    private GotRexDatabase gotRexDatabase;
    public boolean setName = false;
    MediaPlayer song;
    Bitmap b;
    Button cameraBtn;
    ImageView capturedImg, darkBg;
    TextView txt;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    setTitle("Home");
                    transaction.replace(R.id.content, new HomeFragment()).commit();
                    cameraBtn.setVisibility(View.VISIBLE);
                    return true;
                case R.id.navigation_eat:
                    setTitle("Eat");
                    transaction.replace(R.id.content, new EatFragment()).commit();
                    cameraBtn.setVisibility(View.VISIBLE);
                    return true;
                case R.id.navigation_bath:
                    setTitle("Take a Bath");
                    transaction.replace(R.id.content, new BathFragment()).commit();
                    cameraBtn.setVisibility(View.VISIBLE);
                    return true;
                case R.id.navigation_status:
                    setTitle("Status");
                    transaction.replace(R.id.content, new StatusFragment()).commit();
                    cameraBtn.setVisibility(View.INVISIBLE);
                    return true;
                case R.id.navigation_sleep:
                    setTitle("Sleep");
                    transaction.replace(R.id.content, new SleepFragment()).commit();
                    cameraBtn.setVisibility(View.VISIBLE);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        song = MediaPlayer.create(MainActivity.this,R.raw.dino);
        song.setLooping(true);
        song.start();
        String lastAccess = getLastAccess();
        gotRexDatabase = new GotRexDatabase(this);
        gotRexDatabase.open();

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

        //mTextMessage = (TextView) findViewById(R.id.mTextMessage);
        //text = (TextView) findViewById(R.id.text);
        if (lastAccess.equals("") == false) //have used before
        {
            //text.setText("Hello have used this app before" + lastAccess);
            try {
                double newScore = 0;
                SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");

                Date systemDate = Calendar.getInstance().getTime(); //Build variable & get current time
                String currentTime = sdf.format(systemDate); //build up string keep current time

                //parse to date
                Date Date1 = sdf.parse(currentTime);
                Date Date2 = sdf.parse(lastAccess); //put what we get from database here

                //calculate to change status
                long millse = Date1.getTime() - Date2.getTime();
                long mills = Math.abs(millse);
                /*int Hours = (int) (mills/(1000 * 60 * 60));
                int Mins = (int) (mills/(1000*60)) % 60;
                long Secs = (int) (mills / 1000) % 60;*/
                //String diff = Hours + ":" + Mins + ":" + Secs;

                // get second
                double sec = mills / 1000;
                //mTextMessage.setText(String.valueOf(sec));
                //TODO: send second to database to calculate
                gotRexDatabase.reduceStatus(sec);

            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
            mBuilder.setMessage("Here is your new baby, please take care of him!").setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            AlertDialog alert = mBuilder.create();
            alert.setTitle("Welcome to GotRex");
            alert.show();
        }

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationViewHelper.removeShiftMode(navigation);

        setTitle("Home");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content, new HomeFragment()).commit();

    }

    @Override
    public void onDestroy() {
        gotRexDatabase.close();
        song.stop();
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        song.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");

        Date systemDate = Calendar.getInstance().getTime(); //Build variable & get current time
        String myDate = sdf.format(systemDate); //build up string keep current time
        saveLastAccess(myDate);
        song.pause();
    }

    public void saveLastAccess(String lastTime) {
        SharedPreferences sharedPreferences = getSharedPreferences("lastAccessTime", MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("lastAccess", lastTime);
        editor.apply();
    }

    public String getLastAccess() {
        SharedPreferences sharedPreferences = getSharedPreferences("lastAccessTime", MODE_PRIVATE);
        String lastTime = sharedPreferences.getString("lastAccess", "");

        return lastTime;

    }


}
