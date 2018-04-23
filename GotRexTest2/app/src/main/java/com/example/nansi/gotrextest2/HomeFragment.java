package com.example.nansi.gotrextest2;


import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    ImageView imageView;
    AnimationDrawable anim;
    TextView idea;
    private GotRexDatabase gotRexDatabase;
    //CheckGrowth checkGrowth;
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
                value+=5;
                // After 500ms of somewhat tuuing, we consider it actual tuuing
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //imageView.setImageAlpha(128);
                                //imageView.setVisibility(View.INVISIBLE);
                            }
                        });
                        running = false;
                    }
                }, 500);
            }
            idea.setText("Ha hi hi hi...pami");

            if(imageView == null) throw new AssertionError();
            imageView.setBackgroundResource(R.drawable.animation_happy);
            anim = (AnimationDrawable)imageView.getBackground();
            anim.start();

            if (!naJaTuuYuu) {
                cancelTimer();
            }
            //touchID.setText(String.format("(%d, %d) %s %f", x, y, naJaTuuYuu, value));

            return true;
        }
    };

    private boolean withinRect(int x, int y) {
        return imageViewRect.contains(x, y);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        gotRexDatabase = new GotRexDatabase(getActivity());
        gotRexDatabase.open();
        View myGotRex = inflater.inflate(R.layout.fragment_home, container, false);
        //touchID = myGotRex.findViewById(R.id.touchID);
        idea = myGotRex.findViewById(R.id.think);
        imageView = (ImageView)myGotRex.findViewById(R.id.imageBaby);
        if(imageView == null) throw new AssertionError();
        imageView.setBackgroundResource(R.drawable.animation_baby);

        anim = (AnimationDrawable)imageView.getBackground();
        anim.start();

        setIdea();

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

    private void cancelTimer() {
        //imageView.setImageAlpha(255);
        // TODO: ลงดาต้าเบส
        gotRexDatabase.updateHappy(value);
        boolean check = gotRexDatabase.checkGrow();
        CheckGrowth.getGrowth(check, getActivity());

        value = 0;

        if(imageView == null) throw new AssertionError();
        imageView.setBackgroundResource(R.drawable.animation_baby);
        anim = (AnimationDrawable)imageView.getBackground();
        anim.start();

        setIdea();

        //imageView.setVisibility(View.VISIBLE);
        if (running) {
            timer.cancel();
            running = false;
        }
    }

    public void setIdea(){
        String wantTo = null;
        if(gotRexDatabase.pullStatus("hungry") == 0){
            //wantTo.equals("I'm hungry");
            idea.setText("eat eat eat hungry...pami");
        }
        else if (gotRexDatabase.pullStatus("clean") == 0){
            idea.setText("I em ictchy pami!");
        }
        else if (gotRexDatabase.pullStatus("energy") == 0){
            idea.setText("...Zzz...");
        }
        else if (gotRexDatabase.pullStatus("happy") == 0){
            idea.setText("let's play pami...");
        }
        else{
            idea.setText("no idea pami");
        }
    }

}
