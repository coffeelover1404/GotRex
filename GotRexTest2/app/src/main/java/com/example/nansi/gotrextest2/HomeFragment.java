package com.example.nansi.gotrextest2;


import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    ImageView imageView;
    TextView touchID;
    TextView check;
    AnimationDrawable anim;
    double value = 0;

    public HomeFragment() {
        // Required empty public constructor
    }

    Rect imageViewRect;
    Timer timer;
    private boolean running = false;
    private final View.OnTouchListener mTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                view.performClick();
                cancelTimer();
                return true;
            }
            int x = Math.round(motionEvent.getX());
            int y = Math.round(motionEvent.getY());
            boolean naJaTuuYuu = withinRect(x, y);
            if (naJaTuuYuu && !running) {
                running = true;
                timer = new Timer();
                value+=0.01;
                // After 500ms of somewhat tuuing, we consider it actual tuuing
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //imageView.setImageAlpha(128);
                                // TODO: ลงรูปเปลี่ยนแปลง
                                //imageView.setVisibility(View.INVISIBLE);
                            }
                        });
                        running = false;
                    }
                }, 500);
            }
            if (!naJaTuuYuu) {
                cancelTimer();
            }
            touchID.setText(String.format("(%d, %d) %s %f", x, y, naJaTuuYuu, value));
            return true;
        }
    };

    private void cancelTimer() {
        //imageView.setImageAlpha(255);
        // TODO: ลงดาต้าเบส

        value = 0;
        //imageView.setVisibility(View.VISIBLE);
        if (running) {
            timer.cancel();
            running = false;
        }
    }

    private boolean withinRect(int x, int y) {
        return imageViewRect.contains(x, y);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            double newScore = 0;
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");

            Date systemDate = Calendar.getInstance().getTime(); //Build variable & get current time
            String myDate = sdf.format(systemDate); //build up string keep current time
            //check.setText(myDate);

            // TODO: get time from database here

            //parse to date
            Date Date1 = sdf.parse(myDate);
            Date Date2 = sdf.parse("02:50:00"); //put what we get from database here

            //calculate to change status
            long millse = Date1.getTime() - Date2.getTime();
            long mills = Math.abs(millse);
            int Hours = (int) (mills/(1000 * 60 * 60));
            int Mins = (int) (mills/(1000*60)) % 60;
            long Secs = (int) (mills / 1000) % 60;

            //calculate new score
            //newScore = mills;

            String diff = Hours + ":" + Mins + ":" + Secs; // updated value every1 second
            // TODO: put last access back into database
            //check.setText(diff);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View myGotRex = inflater.inflate(R.layout.fragment_home, container, false);
        touchID = myGotRex.findViewById(R.id.touchID);

        check = myGotRex.findViewById(R.id.check); //text for checking time
        /*DateFormat date = new SimpleDateFormat("hh:mm");
        Date dated = null;
        String dateFormat = date.format(Calendar.getInstance().getTime());
        check.setText(dateFormat);*/

        //TextView txtCurrentTime= (TextView)findViewById(R.id.mytext);

        imageView = (ImageView)myGotRex.findViewById(R.id.imageBaby);
        if(imageView == null) throw new AssertionError();
        imageView.setBackgroundResource(R.drawable.animation_baby);

        anim = (AnimationDrawable)imageView.getBackground();
        anim.start();

        myGotRex.setOnTouchListener(mTouchListener);

        imageView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                int height = imageView.getHeight();
                int width = imageView.getWidth();
                int x = imageView.getLeft();
                int y = imageView.getTop();
                imageViewRect = new Rect(x, y, x + width, y + height);
                imageView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });

        return myGotRex;
    }

}
