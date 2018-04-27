package com.example.nansi.gotrextest2;


import android.content.ClipData;
import android.graphics.drawable.AnimationDrawable;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class EatFragment extends Fragment {

    ImageView imageView;
    AnimationDrawable anim;
    int check=0;
    ImageView food1, food2, food3, food4, food5;
    private GotRexDatabase gotRexDatabase;

    public EatFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View myGotRex = inflater.inflate(R.layout.fragment_eat, container, false);

        food1 = (ImageView) myGotRex.findViewById(R.id.food1);
        food2 = (ImageView) myGotRex.findViewById(R.id.food2);
        food3 = (ImageView) myGotRex.findViewById(R.id.food3);
        food4 = (ImageView) myGotRex.findViewById(R.id.food4);
        food5 = (ImageView) myGotRex.findViewById(R.id.food5);

        //animation setting (baby waiting for food)
        imageView = (ImageView)myGotRex.findViewById(R.id.babyEat);
        if(imageView == null) throw new AssertionError();
        imageView.setBackgroundResource(R.drawable.animation_eat);
        //start animation
        anim = (AnimationDrawable)imageView.getBackground();
        anim.start();

        food1.setOnLongClickListener(myClick);
        food2.setOnLongClickListener(myClick);
        food3.setOnLongClickListener(myClick);
        food4.setOnLongClickListener(myClick);
        food5.setOnLongClickListener(myClick);
        imageView.setOnDragListener(dragListener);

        return myGotRex;
    }

   View.OnLongClickListener myClick = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            ClipData data = ClipData.newPlainText("","");
            View.DragShadowBuilder shadow = new View.DragShadowBuilder(v);

            //check the build version to choose the appropriated method
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                v.startDragAndDrop(data, shadow, v, 0);
            } else {
                v.startDrag(data, shadow, v, 0);
            }
            return true;
        }
    };


   View.OnDragListener dragListener = new View.OnDragListener() {
        @Override
        public boolean onDrag(View v, DragEvent event) {

            int dragEvent = event.getAction();
            int numFood = 0;

            switch(dragEvent){
                case DragEvent.ACTION_DRAG_ENTERED: //user starts to hold or select the food
                    //the baby excited move
                    if(imageView == null) throw new AssertionError();
                    imageView.setBackgroundResource(R.drawable.eat5);
                    break;
                case DragEvent.ACTION_DRAG_EXITED: //user throws the food away
                    //the baby move normally
                    if(imageView == null) throw new AssertionError();
                    imageView.setBackgroundResource(R.drawable.animation_eat);
                    break;
                case DragEvent.ACTION_DROP: //user drop food on the baby

                    final View view = (View) event.getLocalState();

                    //the baby chews food
                    if(imageView == null) throw new AssertionError();
                    imageView.setBackgroundResource(R.drawable.animation_chew);
                    anim = (AnimationDrawable)imageView.getBackground();
                    anim.start();

                    //check the food score
                    if(view.getId() == R.id.food1) numFood = 20;
                    else if(view.getId() == R.id.food2) numFood = 5;
                    else if(view.getId() == R.id.food3) numFood = 30;
                    else if(view.getId() == R.id.food4) numFood = 10;
                    else if(view.getId() == R.id.food1) numFood = 30;

                    if(numFood!=0) {
                        gotRexDatabase = new GotRexDatabase(getActivity());
                        gotRexDatabase.open();
                        gotRexDatabase.updateEat(numFood);
                        boolean check = gotRexDatabase.checkGrow();
                        CheckGrowth.getGrowth(check, getActivity());
                    }

                    break;
            }
            return true;
        }
    };



}