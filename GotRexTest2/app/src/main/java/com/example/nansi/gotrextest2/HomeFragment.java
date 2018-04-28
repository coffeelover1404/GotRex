//courtesy by Thatchapon Unprasert (5888) on function mTouchListener, withinRect and few parts of cancelTimer
// for checking the user rubbing their finger on the screen
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
                // After 500ms of somewhat rubbing, we consider it actual rubbing
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        running = false;
                    }
                }, 500);
            }
            idea.setText("Ha hi hi hi...pami");

            if(imageView == null) throw new AssertionError();
            imageView.setBackgroundResource(R.drawable.animation_happy);
            anim = (AnimationDrawable)imageView.getBackground();
            anim.start();

            //if the user stop rubbing cancel the timer
            if (!naJaTuuYuu) {
                cancelTimer();
            }

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
        idea = myGotRex.findViewById(R.id.think);
        imageView = myGotRex.findViewById(R.id.imageBaby);
        if(imageView == null) throw new AssertionError();
        imageView.setBackgroundResource(R.drawable.animation_baby);

        anim = (AnimationDrawable)imageView.getBackground();
        anim.start();

        setIdea();

        myGotRex.setOnTouchListener(mTouchListener);

        //get the area to listen user onTouch
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
        //if the user stop rubbing
        //add the score on database
        gotRexDatabase.updateHappy(value);
        boolean check = gotRexDatabase.checkGrow();
        CheckGrowth.getGrowth(check, getActivity());

        //set value (score) back to 0
        value = 0;

        if(imageView == null) throw new AssertionError();
        imageView.setBackgroundResource(R.drawable.animation_baby);
        anim = (AnimationDrawable)imageView.getBackground();
        anim.start();

        setIdea();

        if (running) {
            timer.cancel();
            running = false;
        }
    }

    public void setIdea(){
        if(gotRexDatabase.pullStatus("hungry") == 0){
            idea.setText("eat eat eat hungry...pami");
        }
        else if (gotRexDatabase.pullStatus("clean") == 0){
            idea.setText("I em itchy pami!");
        }
        else if (gotRexDatabase.pullStatus("energy") == 0){
            idea.setText("...Zzz...");
        }
        else if (gotRexDatabase.pullStatus("happy") == 0){
            idea.setText("let's play pami...");
        }
        else{
            idea.setText("...pami");
        }
    }

}
